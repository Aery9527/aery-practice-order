package org.aery.practice.order.portal.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface OrderPortalLoader extends ComponentLoader<OrderPortal, OrderPortalInfo> {

    static OrderPortalLoader create() {
        ServiceLoader<OrderPortalLoader> serviceLoader = ServiceLoader.load(OrderPortalLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
