package org.aery.practice.order.utils.schedule;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.aery.practice.order.utils.config.LocateEnableConfig;
import org.aery.practice.order.utils.locate.tool.LocateNodeHeartbeatTarget;
import org.aery.practice.order.utils.mark.conditional.ConditionalNotTestProfile;
import org.misty.utils.StackFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnBean(LocateEnableConfig.class)
@ConditionalNotTestProfile
public class LocateNodeHeartbeatSchedule {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 心跳間隔時間
     */
    private final long heartbeatMs = 5_000;

    /**
     * 要在多久內完成 heartbeat 才算成功, 超過這個秒數還沒有 heartbeat 結束就算異常
     */
    private final long regardedSuccessMs = heartbeatMs / 5;

    @Autowired
    private LocateNodeHeartbeatTarget heartbeatTarget;

    /**
     * 因為這個邏輯很需要精確的排程執行時間,
     * 若使用 {@link Scheduled} 很容易因為會跟整個 spring context 其他地方有使用到的占用同一條 thread 造成延遲問題,
     * 雖然可以使用 {@link ThreadPoolTaskScheduler} 調整 {@link Scheduled} 使用 thread pool 來避免這個問題,
     * 但依然會有塞車風險, 為保險起見還是由這邊自己使用 {@link ScheduledExecutorService} 來控制.
     */
    private ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    public void start() {
        if (this.scheduledExecutorService == null) {
            this.logger.info("start ScheduledExecutorService");

            this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            this.scheduledExecutorService.scheduleAtFixedRate(() -> {
                try {
                    this.heartbeatTarget.heartbeat();
                } catch (Exception e) {
                    this.logger.error("heartbeat exception : ", e);
                }
            }, getRegardedSuccessMs(), getHeartbeatMs(), TimeUnit.MILLISECONDS);
        } else {
            this.logger.info("start ScheduledExecutorService ignore");
        }
    }

    @PreDestroy
    public void stop() {
        if (this.scheduledExecutorService == null) {
            this.logger.warn("stop ScheduledExecutorService ignore");
        } else {
            StringBuilder callStack = StackFetcher.fetchStacksPretty(1);
            this.logger.warn("stop ScheduledExecutorService by{}{}", System.lineSeparator(), callStack);
            this.scheduledExecutorService.shutdown();
            this.scheduledExecutorService = null;
        }
    }

    public long getHeartbeatMs() {
        return this.heartbeatMs;
    }

    public long getRegardedSuccessMs() {
        return this.regardedSuccessMs;
    }

}
