package org.aery.practice.order.utils.locate.tool;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public interface LocateNodeHeartbeatEngine {

    void registerOfflineReceiver(Runnable receiver);

    void registerLocateReceiver(IntConsumer receiver);

    void registerAliveNodesReceiver(Consumer<Set<String>> receiver);

}
