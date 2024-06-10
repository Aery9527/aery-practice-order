package org.aery.practice.order.matcher.core;

import org.aery.practice.order.matcher.api.OrderMatcher;
import org.aery.practice.order.matcher.api.OrderMatcherInfo;
import org.aery.practice.order.matcher.api.OrderMatcherLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class OrderMatcherByCoreLoader extends ComponentLoaderAbstract<OrderMatcher, OrderMatcherInfo> implements OrderMatcherLoader {

    @Override
    public Class<? extends OrderMatcher> getCoreType() {
        return OrderMatcherByCore.class;
    }

}
