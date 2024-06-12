package org.aery.practice.order.utils.locate.impl;

import jakarta.annotation.PostConstruct;
import org.aery.practice.order.utils.locate.api.LocateCluster;
import org.aery.practice.order.utils.locate.tool.LocateNodeHeartbeatEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class LocateClusterByHeartbeat implements LocateCluster {

    private final AtomicReference<Set<String>> aliveNodes = new AtomicReference<>(Collections.emptySet());

    @Autowired
    private LocateNodeHeartbeatEngine heartbeatEngine;

    @PostConstruct
    public void initial() {
        initial_AliveNodesSignal(this.heartbeatEngine);
    }

    public void initial_AliveNodesSignal(LocateNodeHeartbeatEngine heartbeatEngine) {
        heartbeatEngine.receiveSignalWhenAliveNodes(aliveNodes -> {
            this.aliveNodes.set(Collections.unmodifiableSet(aliveNodes));
        });
    }

    @Override
    public String clusterName() {
        return ""; // TODO
    }

    @Override
    public Set<String> getAliveNodes() {
        return this.aliveNodes.get();
    }

}
