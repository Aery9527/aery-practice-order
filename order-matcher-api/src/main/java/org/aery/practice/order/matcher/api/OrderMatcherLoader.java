package org.aery.practice.order.matcher.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface OrderMatcherLoader extends ComponentLoader<OrderMatcher, OrderMatcherInfo> {

    static OrderMatcherLoader create() {
        ServiceLoader<OrderMatcherLoader> serviceLoader = ServiceLoader.load(OrderMatcherLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
