package org.aery.practice.order.portal.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface PortalLoader extends ComponentLoader<Portal, PortalInfo> {

    static PortalLoader create() {
        ServiceLoader<PortalLoader> serviceLoader = ServiceLoader.load(PortalLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
