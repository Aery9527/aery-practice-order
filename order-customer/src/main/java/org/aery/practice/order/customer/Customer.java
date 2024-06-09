package org.aery.practice.order.customer;

import org.aery.practice.order.portal.api.Portal;
import org.aery.practice.order.utils.Component;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Customer implements Component<CustomerInfo> {

    private final CustomerInfo info = new CustomerInfo("customer");

    @Override
    public CustomerInfo getInfo() {
        return this.info;
    }

    public void simulate(Portal portal) {

    }

}
