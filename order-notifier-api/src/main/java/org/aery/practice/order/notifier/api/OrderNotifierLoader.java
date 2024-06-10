package org.aery.practice.order.notifier.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface OrderNotifierLoader extends ComponentLoader<OrderNotifier, OrderNotifierInfo> {

    static OrderNotifierLoader create() {
        ServiceLoader<OrderNotifierLoader> serviceLoader = ServiceLoader.load(OrderNotifierLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
