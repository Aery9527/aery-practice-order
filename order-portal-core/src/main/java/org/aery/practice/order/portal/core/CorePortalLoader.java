package org.aery.practice.order.portal.core;

import org.aery.practice.order.portal.api.Portal;
import org.aery.practice.order.portal.api.PortalInfo;
import org.aery.practice.order.portal.api.PortalLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class CorePortalLoader extends ComponentLoaderAbstract<Portal, PortalInfo> implements PortalLoader {

    public static void main(String[] args) {
        new CorePortalLoader().start(args);
    }

    @Override
    public Class<? extends CorePortal> getTargetType() {
        return CorePortal.class;
    }

}
