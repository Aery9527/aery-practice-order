package org.aery.practice.order.utils.locate.tool;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

@Component
public class LocateNodeHeartbeatEngineByRedis implements LocateNodeHeartbeatEngine {

    @Override
    public void receiveSignalWhenOffline(Runnable receiver) {
        // TODO
    }

    @Override
    public void receiveSignalWhenLocate(IntConsumer receiver) {
        // TODO
    }

    @Override
    public void receiveSignalWhenAliveNodes(Consumer<Set<String>> receiver) {
        // TODO
    }

}
