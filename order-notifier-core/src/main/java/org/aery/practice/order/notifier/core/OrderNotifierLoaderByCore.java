package org.aery.practice.order.notifier.core;

import org.aery.practice.order.notifier.api.OrderNotifier;
import org.aery.practice.order.notifier.api.OrderNotifierInfo;
import org.aery.practice.order.notifier.api.OrderNotifierLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class OrderNotifierLoaderByCore extends ComponentLoaderAbstract<OrderNotifier, OrderNotifierInfo> implements OrderNotifierLoader {

    @Override
    public Class<? extends OrderNotifier> getCoreType() {
        return OrderNotifierByCore.class;
    }

}
