package org.aery.practice.order.utils.locate.tool;

import org.aery.practice.order.utils.locate.api.LocateNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocateNodeHeartbeatRedisKeys {

    @Autowired
    private LocateNode locateNode;

    public String getHeartbeatKey() {
        String nodeId = this.locateNode.getNodeId();
        return "locate:node:heartbeat:" + nodeId;
    }

}
