package org.aery.practice.order.utils.locate.impl;

import jakarta.annotation.PostConstruct;
import org.aery.practice.order.utils.bean.api.UtilsVerifier;
import org.aery.practice.order.utils.locate.api.LocateNode;
import org.aery.practice.order.utils.locate.tool.LocateNodeHeartbeatEngine;
import org.misty.utils.IdentityGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LocateNodeByHeartbeat implements LocateNode {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger locate = new AtomicInteger(LocateNode.INITIAL_LOCATE);

    private final AtomicBoolean online = new AtomicBoolean(false);

    private final String nodeId;

    @Autowired
    private UtilsVerifier verifier;

    @Autowired
    private LocateNodeHeartbeatEngine heartbeatEngine;

    public LocateNodeByHeartbeat() {
        this.nodeId = IdentityGenerator.genWithTimestamp(5, 1);
    }

    @PostConstruct
    public void initial() {
        this.logger.info("nodeId is {}", this.nodeId);
        initial_OfflineSignal(this.heartbeatEngine);
        initial_LocateSignal(this.heartbeatEngine);
    }

    public void initial_OfflineSignal(LocateNodeHeartbeatEngine heartbeatEngine) {
        heartbeatEngine.receiveSignalWhenOffline(() -> {
            this.online.set(false);
            this.locate.set(LocateNode.OFFLINE_LOCATE);
        });
    }

    public void initial_LocateSignal(LocateNodeHeartbeatEngine heartbeatEngine) {
        String msgFormat = "node locate change from {} to {}";
        String from_initial = "initial(" + LocateNode.INITIAL_LOCATE + ")";
        String from_offline = "offline(" + LocateNode.OFFLINE_LOCATE + ")";

        heartbeatEngine.receiveSignalWhenLocate(locate -> {
            this.verifier.requireIntMoreThanInclusive("locate", locate, 1);

            if (this.locate.get() == locate) {
                return;
            }

            String from;
            if (this.locate.get() == LocateNode.INITIAL_LOCATE) {
                from = from_initial;
            } else if (this.locate.get() == LocateNode.OFFLINE_LOCATE) {
                from = from_offline;
            } else {
                from = String.valueOf(locate);
            }
            this.logger.info(msgFormat, from, locate);

            this.locate.set(locate);
            this.online.set(true);
        });
    }

    @Override
    public String getNodeId() {
        return this.nodeId;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public int getLocate() {
        return this.locate.get();
    }

    @Override
    public boolean isOnlineAndLocateWhen(int locate) {
        if (this.online.get()) {
            return this.locate.get() == locate;
        } else {
            return false;
        }
    }


}
