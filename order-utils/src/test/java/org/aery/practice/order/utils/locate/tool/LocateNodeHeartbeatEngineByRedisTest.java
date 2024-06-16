package org.aery.practice.order.utils.locate.tool;

import jakarta.annotation.PostConstruct;
import org.aery.practice.order.utils.config.LocateEnableConfig;
import org.aery.practice.order.utils.error.GlobalException;
import org.aery.practice.order.utils.fortest.CommonTestConfig;
import org.aery.practice.order.utils.fortest.ForRedisTestConfig;
import org.aery.practice.order.utils.schedule.LocateNodeHeartbeatSchedule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.misty.test.AssertionsEx;
import org.misty.utils.fi.FunctionEx;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LocateEnableConfig.class, ForRedisTestConfig.class})
@CommonTestConfig
class LocateNodeHeartbeatEngineByRedisTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Field engineByRedis_offlineAction;

    private final Field engineByRedis_locateAction;

    private final Field engineByRedis_aliveNodesAction;

    @Autowired(required = false)
    private LocateNodeHeartbeatSchedule heartbeatSchedule;

    @Autowired
    private LocateNodeHeartbeatEngineByRedis heartbeatEngineByRedis;

    public LocateNodeHeartbeatEngineByRedisTest() {
        Class<LocateNodeHeartbeatEngineByRedis> targetClass = LocateNodeHeartbeatEngineByRedis.class;

        FunctionEx<String, Field> fetchField = fieldName -> {
            Field field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        };

        this.engineByRedis_offlineAction = fetchField.execute("offlineAction");
        this.engineByRedis_locateAction = fetchField.execute("locateAction");
        this.engineByRedis_aliveNodesAction = fetchField.execute("aliveNodesAction");
    }

    private AtomicReference<Runnable> getOfflineActionFromEngineByRedis() throws IllegalAccessException {
        return (AtomicReference<Runnable>) this.engineByRedis_offlineAction.get(this.heartbeatEngineByRedis);
    }

    private AtomicReference<IntConsumer> getLocateActionFromEngineByRedis() throws IllegalAccessException {
        return (AtomicReference<IntConsumer>) this.engineByRedis_locateAction.get(this.heartbeatEngineByRedis);
    }

    private AtomicReference<Consumer<Set<String>>> getAliveNodesActionFromEngineByRedis() throws IllegalAccessException {
        return (AtomicReference<Consumer<Set<String>>>) this.engineByRedis_aliveNodesAction.get(this.heartbeatEngineByRedis);
    }

    @PostConstruct
    public void initial() {
        System.out.println("initial");
    }

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() throws IllegalAccessException {
        Assertions.assertThat(this.heartbeatSchedule).isNull();

        getOfflineActionFromEngineByRedis().set(null);
        getLocateActionFromEngineByRedis().set(null);
        getAliveNodesActionFromEngineByRedis().set(null);
    }

    @Test
    void registerOfflineReceiver() throws IllegalAccessException {
        Runnable receiver = Mockito.mock(Runnable.class);

        this.heartbeatEngineByRedis.registerOfflineReceiver(receiver); // success

        Runnable offlineAction = getOfflineActionFromEngineByRedis().get();
        offlineAction.run();

        Mockito.verify(receiver).run();

        AssertionsEx.assertThrown(() -> this.heartbeatEngineByRedis.registerOfflineReceiver(receiver))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("offline"); // reject
    }

    @Test
    void registerLocateReceiver() throws IllegalAccessException {
        int locate = new Random().nextInt();

        IntConsumer receiver = Mockito.mock(IntConsumer.class);
        ArgumentCaptor<Integer> arg = ArgumentCaptor.forClass(Integer.class);
        Mockito.doNothing().when(receiver).accept(arg.capture());

        this.heartbeatEngineByRedis.registerLocateReceiver(receiver); // success

        IntConsumer locateAction = getLocateActionFromEngineByRedis().get();
        locateAction.accept(locate);

        AssertionsEx.assertThat(arg.getValue()).isEqualTo(locate);

        AssertionsEx.assertThrown(() -> this.heartbeatEngineByRedis.registerLocateReceiver(receiver))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("locate"); // reject
    }

    @Test
    void registerAliveNodesReceiver() throws IllegalAccessException {
        Set<String> aliveNodes = new HashSet<>();

        Consumer<Set<String>> receiver = Mockito.mock(Consumer.class);
        ArgumentCaptor<Set<String>> arg = ArgumentCaptor.forClass(Set.class);
        Mockito.doNothing().when(receiver).accept(arg.capture());

        this.heartbeatEngineByRedis.registerAliveNodesReceiver(receiver); // success

        Consumer<Set<String>> aliveNodesAction = getAliveNodesActionFromEngineByRedis().get();
        aliveNodesAction.accept(aliveNodes);

        AssertionsEx.assertThat(arg.getValue()).isEqualTo(aliveNodes);

        AssertionsEx.assertThrown(() -> this.heartbeatEngineByRedis.registerAliveNodesReceiver(receiver))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("aliveNodes"); // reject
    }

}
