package org.aery.practice.order.utils.locate.tool;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public interface LocateNodeHeartbeatEngine {

    void receiveSignalWhenOffline(Runnable receiver);

    void receiveSignalWhenLocate(IntConsumer receiver);

    void receiveSignalWhenAliveNodes(Consumer<Set<String>> receiver);

}
