package org.aery.practice.order.utils.locate.api;

import java.util.Set;

public interface LocateCluster {

    String clusterName();

    /**
     * 目前共有哪幾台node存活
     */
    Set<String> getAliveNodes();

    default int getAliveNodesNumber() {
        return getAliveNodes().size();
    }

}
