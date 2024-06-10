package org.aery.practice.order.customer;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class OrderCustomerLoader
        extends ComponentLoaderAbstract<OrderCustomer, OrderCustomerInfo>
        implements ComponentLoader<OrderCustomer, OrderCustomerInfo> {

    public static OrderCustomerLoader create() {
        return new OrderCustomerLoader();
    }

    @Override
    public Class<? extends OrderCustomer> getCoreType() {
        return OrderCustomer.class;
    }

}
