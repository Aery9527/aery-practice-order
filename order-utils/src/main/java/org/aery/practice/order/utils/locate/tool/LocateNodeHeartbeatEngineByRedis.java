package org.aery.practice.order.utils.locate.tool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.aery.practice.order.utils.error.ErrorCode;
import org.aery.practice.order.utils.locate.api.LocateCluster;
import org.aery.practice.order.utils.locate.api.LocateNode;
import org.misty.utils.StackFetcher;
import org.misty.utils.ex.ThreadEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

@Component
public class LocateNodeHeartbeatEngineByRedis implements LocateNodeHeartbeatEngine, LocateNodeHeartbeatTarget {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicReference<Runnable> offlineAction = new AtomicReference<>(null);

    private final AtomicReference<IntConsumer> locateAction = new AtomicReference<>(null);

    private final AtomicReference<Consumer<Set<String>>> aliveNodesAction = new AtomicReference<>(null);

    private final Map<String, Double> aliveNodeMap = new LinkedHashMap<>();

    /**
     * 心跳間隔時間
     */
    private final long heartbeatMs = 5_000;

    /**
     * 要在多久內完成 heartbeat 才算成功, 超過這個秒數 heartbeat 還沒有結束就算異常
     */
    private final long regardedSuccessMs = heartbeatMs / 5;

    /**
     * 節點的 heartbeat 緩衝次數, 超過這個次數沒有更新則視為離線,
     * 因為 redis zset score 是 double, 因此這邊也使用 double, 避免無意義的轉型浪費效能.
     * <br>
     * 使用小於接近2的數, 是避免精度造成的誤差, 反正在意義上就是第二次還是沒有 heartbeat 就視為失敗.
     */
    private final double heartbeatBufferTimes = 2 - Double.MIN_NORMAL;

    @Autowired
    private LocateNode locateNode;

    @Autowired
    private LocateCluster locateCluster;

    @Autowired
    private LocateNodeHeartbeatRedisKeys redisKey;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private BoundZSetOperations<String, Object> boundZsetOps;

    private ExecutorService heartbeatWatcher;

    @PostConstruct
    public void initial() {
        String heartbeatKey = this.redisKey.getHeartbeatKey();
        this.boundZsetOps = this.redisTemplate.boundZSetOps(heartbeatKey);
        this.heartbeatWatcher = Executors.newSingleThreadExecutor();
    }

    @PreDestroy
    public void destroy() {
        this.heartbeatWatcher.shutdown();
    }

    @Override
    public void registerOfflineReceiver(Runnable receiver) {
        boolean success = this.offlineAction.compareAndSet(null, () -> {
            this.aliveNodeMap.clear();
            receiver.run();
            this.aliveNodesAction.get().accept(Collections.emptySet());
        });

        if (success) {
            StackTraceElement ste = StackFetcher.fetchStack(1);
            this.logger.info("offline receiver setup by {}", ste);
        } else {
            ErrorCode.INCORRECT_STATE.thrown("can't set offline receiver again.");
        }
    }

    @Override
    public void registerLocateReceiver(IntConsumer receiver) {
        boolean success = this.locateAction.compareAndSet(null, receiver);

        if (success) {
            StackTraceElement ste = StackFetcher.fetchStack(1);
            this.logger.info("locate receiver setup by {}", ste);
        } else {
            ErrorCode.INCORRECT_STATE.thrown("can't set locate receiver again.");
        }
    }

    @Override
    public void registerAliveNodesReceiver(Consumer<Set<String>> receiver) {
        boolean success = this.aliveNodesAction.compareAndSet(null, receiver);

        if (success) {
            StackTraceElement ste = StackFetcher.fetchStack(1);
            this.logger.info("aliveNodes receiver setup by {}", ste);
        } else {
            ErrorCode.INCORRECT_STATE.thrown("can't set aliveNodes receiver again.");
        }
    }

    @Override
    public long getHeartbeatMs() {
        return this.heartbeatMs;
    }

    public long getRegardedSuccessMs() {
        return this.regardedSuccessMs;
    }

    @Override
    public void heartbeat() {
        boolean updatedInTime = updateRedis();
        if (updatedInTime) {
            Map<String, Double> currentAliveNodes = fetchCurrentAliveNodesFromRedis();
            updatePresentNodeLocate(currentAliveNodes); // 先更新當前節點的 locate
            updateAliveNodeMap(currentAliveNodes); // 更新 aliveNodeMap
            findAndRemoveLegacyNodesOnRedis(currentAliveNodes); // 檢查並移除過時的節點
        }
    }

    public boolean updateRedis() {
        String scoreKey = this.locateNode.getNodeId();
        AtomicBoolean heartbeatFinish = new AtomicBoolean(false);
        AtomicBoolean updateInTime = new AtomicBoolean(true);

        this.heartbeatWatcher.execute(() -> {
            ThreadEx.rest(getRegardedSuccessMs());
            boolean heartbeatFinished = heartbeatFinish.get();
            updateInTime.set(heartbeatFinished);

            if (!heartbeatFinished) {
                this.logger.warn("redis heartbeat over {}ms, make node offline and suspend update node locate this time.", this.regardedSuccessMs);
                this.offlineAction.get().run();
            }
        });

        this.boundZsetOps.incrementScore(scoreKey, 1); // maybe block a while
        boolean updatedInTime = updateInTime.get();
        heartbeatFinish.set(true);

        return updatedInTime;
    }

    public Map<String, Double> fetchCurrentAliveNodesFromRedis() {
        return Collections.unmodifiableMap(
                this.boundZsetOps.rangeWithScores(0, -1)
                        .stream()
                        .collect(LinkedHashMap::new, (map, tuple) -> map.put((String) tuple.getValue(), tuple.getScore()), Map::putAll)
        );
    }

    public void updatePresentNodeLocate(Map<String, Double> currentAliveNodes) {
        String presentNodeId = this.locateNode.getNodeId();
        int locate = 1;
        for (String aliveNodeId : currentAliveNodes.keySet()) {
            if (aliveNodeId.equals(presentNodeId)) {
                this.locateAction.get().accept(locate);
                return;
            }
            locate++;
        }
    }

    public void updateAliveNodeMap(Map<String, Double> currentAliveNodes) {
        Set<String> extraNodes = currentAliveNodes.entrySet().stream()
                .filter(entry -> {
                    String currentAliveNodeId = entry.getKey();
                    Double currentAliveNodeScore = entry.getValue();
                    Double oldScore = this.aliveNodeMap.put(currentAliveNodeId, currentAliveNodeScore);
                    return oldScore == null;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        Set<String> legacyNodes = this.aliveNodeMap.keySet().stream()
                .filter(olderNodeId -> !currentAliveNodes.containsKey(olderNodeId))
                .collect(Collectors.toSet());

        boolean aliveNodeMapNoChange = extraNodes.isEmpty() && legacyNodes.isEmpty();
        if (aliveNodeMapNoChange) {
            return;
        }

        this.aliveNodeMap.putAll(currentAliveNodes);
        legacyNodes.forEach(this.aliveNodeMap::remove); // 這邊有出現移除的 node 的話, 是因為上一輪經過 node 清理才會有的

        this.logger.info("aliveNodeMap updated, extra:{}, legacy:{}, now:{}", extraNodes, legacyNodes, this.aliveNodeMap.keySet());

        Set<String> newNodes = this.aliveNodeMap.keySet().stream().collect(Collectors.toUnmodifiableSet());
        this.aliveNodesAction.get().accept(newNodes);
    }

    public void findAndRemoveLegacyNodesOnRedis(Map<String, Double> currentAliveNodes) {
        Set<String> legacyNodes = this.aliveNodeMap.entrySet().stream()
                .filter(entry -> { // 找出過時的節點
                    String olderNodeId = entry.getKey();
                    Double olderScore = entry.getValue();

                    Double currentScore = currentAliveNodes.get(olderNodeId); // 理論上不會找不到
                    double scoreGap = currentScore - olderScore;
                    return scoreGap >= this.heartbeatBufferTimes;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (legacyNodes.isEmpty()) {
            return;
        }

        boolean isPresentFirstAliveNode = checkPresentIsFirstAliveNode(currentAliveNodes, legacyNodes);
        if (isPresentFirstAliveNode) {
            removeLegacyNodesOnRedis(legacyNodes);
        }
    }

    public boolean checkPresentIsFirstAliveNode(Map<String, Double> currentAliveNodes, Set<String> legacyNodes) {
        String presentNodeId = this.locateNode.getNodeId();
        String firstNodeId = currentAliveNodes.keySet().stream()
                .filter(nodeId -> !legacyNodes.contains(nodeId))
                .findFirst()
                .get();
        return presentNodeId.equals(firstNodeId);
    }

    public void removeLegacyNodesOnRedis(Set<String> legacyNodes) {
        this.boundZsetOps.remove(legacyNodes.toArray());
    }

}
