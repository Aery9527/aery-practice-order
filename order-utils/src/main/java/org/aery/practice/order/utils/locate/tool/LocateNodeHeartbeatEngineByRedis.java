package org.aery.practice.order.utils.locate.tool;

import org.aery.practice.order.utils.error.ErrorCode;
import org.misty.utils.StackFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

@Component
public class LocateNodeHeartbeatEngineByRedis implements LocateNodeHeartbeatEngine, LocateNodeHeartbeatTarget {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final long UPDATE_REDIS_HEARTBEAT_ALLOW_MS = 1000L;

    private final AtomicReference<Runnable> offlineAction = new AtomicReference<>();

    private final AtomicReference<IntConsumer> locateAction = new AtomicReference<>();

    private final AtomicReference<Consumer<Set<String>>> aliveNodesAction = new AtomicReference<>();

    private final Map<String, Double> aliveNodeMap = new TreeMap<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void receiveSignalWhenOffline(Runnable receiver) {
        boolean success = this.offlineAction.compareAndSet(null, () -> {
            this.aliveNodeMap.clear();
            receiver.run();
        });

        if (success) {
            StackTraceElement ste = StackFetcher.fetchStack(1);
            this.logger.info("offline receiver setup by {}", ste);
        } else {
            ErrorCode.INCORRECT_STATE.thrown("can't set offline receiver again.");
        }
    }

    @Override
    public void receiveSignalWhenLocate(IntConsumer receiver) {
        boolean success = this.locateAction.compareAndSet(null, receiver);

        if (success) {
            StackTraceElement ste = StackFetcher.fetchStack(1);
            this.logger.info("locate receiver setup by {}", ste);
        } else {
            ErrorCode.INCORRECT_STATE.thrown("can't set locate receiver again.");
        }
    }

    @Override
    public void receiveSignalWhenAliveNodes(Consumer<Set<String>> receiver) {
        boolean success = this.aliveNodesAction.compareAndSet(null, receiver);

        if (success) {
            StackTraceElement ste = StackFetcher.fetchStack(1);
            this.logger.info("aliveNodes receiver setup by {}", ste);
        } else {
            ErrorCode.INCORRECT_STATE.thrown("can't set aliveNodes receiver again.");
        }
    }

    @Override
    public void heartbeat() {

    }

}
