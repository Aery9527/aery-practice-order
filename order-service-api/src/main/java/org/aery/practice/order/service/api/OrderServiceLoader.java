package org.aery.practice.order.service.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface OrderServiceLoader extends ComponentLoader<OrderService, OrderServiceInfo> {

    static OrderServiceLoader create() {
        ServiceLoader<OrderServiceLoader> serviceLoader = ServiceLoader.load(OrderServiceLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
