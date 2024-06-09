package org.aery.practice.order.customer;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class CustomerLoader
        extends ComponentLoaderAbstract<Customer, CustomerInfo>
        implements ComponentLoader<Customer, CustomerInfo> {

    public static CustomerLoader create() {
        return new CustomerLoader();
    }

    @Override
    public Class<? extends Customer> getTargetType() {
        return Customer.class;
    }

}
