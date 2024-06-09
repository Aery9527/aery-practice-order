package org.aery.practice.order.protal.http;

import org.aery.practice.order.portal.api.Portal;
import org.aery.practice.order.portal.api.PortalInfo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpPortal implements Portal {

    private final PortalInfo info = new PortalInfo("portal-http");

    @Override
    public PortalInfo getInfo() {
        return this.info;
    }

}
