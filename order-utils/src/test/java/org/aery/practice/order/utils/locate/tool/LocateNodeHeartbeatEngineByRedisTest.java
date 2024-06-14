package org.aery.practice.order.utils.locate.tool;

import org.aery.practice.order.utils.config.LocateEnableConfig;
import org.aery.practice.order.utils.fortest.CommonTestConfig;
import org.aery.practice.order.utils.fortest.ForRedisTestConfig;
import org.aery.practice.order.utils.schedule.LocateNodeHeartbeatSchedule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LocateEnableConfig.class, ForRedisTestConfig.class})
@CommonTestConfig
class LocateNodeHeartbeatEngineByRedisTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private LocateNodeHeartbeatSchedule heartbeatSchedule;

    @Autowired
    private LocateNodeHeartbeatEngineByRedis heartbeatEngineByRedis;

    @BeforeEach
    void setUp() {
        Assertions.assertThat(this.heartbeatSchedule).isNull();
    }

    @Test
    void receiveSignalWhenOffline() {

    }

    @Test
    void receiveSignalWhenLocate() {

    }

    @Test
    void receiveSignalWhenAliveNodes() {

    }

}
