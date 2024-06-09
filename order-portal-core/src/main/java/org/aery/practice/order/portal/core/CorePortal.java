package org.aery.practice.order.portal.core;

import org.aery.practice.order.portal.api.Portal;
import org.aery.practice.order.portal.api.PortalInfo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CorePortal implements Portal {

    private final PortalInfo info = new PortalInfo("core-portal");

    @Override
    public PortalInfo getInfo() {
        return this.info;
    }

}
