package org.aery.practice.order.service.core;

import org.aery.practice.order.service.api.OrderService;
import org.aery.practice.order.service.api.OrderServiceInfo;
import org.aery.practice.order.service.api.OrderServiceLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class OrderServiceByCoreLoader extends ComponentLoaderAbstract<OrderService, OrderServiceInfo> implements OrderServiceLoader {

    @Override
    public Class<? extends OrderService> getCoreType() {
        return OrderServiceByCore.class;
    }

}
