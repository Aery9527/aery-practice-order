package org.aery.practice.order.utils.schedule;

import org.aery.practice.order.utils.fortest.CommonTestConfig;
import org.aery.practice.order.utils.locate.tool.LocateNodeHeartbeatTarget;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.utils.ex.ThreadEx;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.IntConsumer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@CommonTestConfig
@DirtiesContext
class LocateNodeHeartbeatScheduleTest {

    @Configuration
    @ConditionalOnBean(LocateNodeHeartbeatScheduleTest.class) // 不加這個會影響其他 test, 挺怪的...
    public static class Config {

        /**
         * 因為 {@link CommonTestConfig} 啟用 test profile, 會禁止 {@link LocateNodeHeartbeatSchedule} 啟用, 所以這邊主動加入用以測試
         */
        @Bean
        LocateNodeHeartbeatSchedule heartbeatSchedule() {
            return new LocateNodeHeartbeatSchedule();
        }
    }

    @SpyBean
    private LocateNodeHeartbeatSchedule heartbeatSchedule;

    @MockBean
    private LocateNodeHeartbeatTarget heartbeatTarget;

    @BeforeEach
    void setUp() {
        this.heartbeatSchedule.stop();
        Mockito.reset(this.heartbeatSchedule);
        Mockito.reset(this.heartbeatTarget);
    }

    @Test
    void heartbeat() {
        long heartbeatMs = 1000;
        long resetForTest = heartbeatMs + 100;

        Mockito.doReturn(heartbeatMs).when(this.heartbeatSchedule).getHeartbeatMs();

        this.heartbeatSchedule.start();

        IntConsumer testHeartbeat = expectedTimes -> {
            ThreadEx.rest(resetForTest);
            Mockito.verify(this.heartbeatTarget, Mockito.times(expectedTimes)).heartbeat();
        };
        testHeartbeat.accept(1);
        testHeartbeat.accept(2);
    }

    @Test
    void getHeartbeatMs() {
        Assertions.assertThat(this.heartbeatSchedule.getHeartbeatMs()).isEqualTo(5_000);
    }

    @Test
    void getRegardedSuccessMs() {
        Assertions.assertThat(this.heartbeatSchedule.getRegardedSuccessMs()).isEqualTo(5_000 / 5);
    }

}
