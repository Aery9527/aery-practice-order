package org.aery.practice.order.utils.locate.tool;

public interface LocateNodeHeartbeatTarget {

    long getHeartbeatMs();

    void heartbeat();

}
