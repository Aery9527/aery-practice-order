package org.aery.practice.order.protal.http;

import org.aery.practice.order.portal.api.Portal;
import org.aery.practice.order.portal.api.PortalInfo;
import org.aery.practice.order.portal.api.PortalLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class HttpPortalLoader extends ComponentLoaderAbstract<Portal, PortalInfo> implements PortalLoader {

    public static void main(String[] args) {
        new HttpPortalLoader().start(args);
    }

    @Override
    public Class<? extends HttpPortal> getTargetType() {
        return HttpPortal.class;
    }

}
