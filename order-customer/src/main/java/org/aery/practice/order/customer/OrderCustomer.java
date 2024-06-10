package org.aery.practice.order.customer;

import org.aery.practice.order.portal.api.OrderPortal;
import org.aery.practice.order.utils.Component;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderCustomer implements Component<OrderCustomerInfo> {

    private final OrderCustomerInfo info = new OrderCustomerInfo("order-customer");

    @Override
    public OrderCustomerInfo getInfo() {
        return this.info;
    }

    public void simulate(OrderPortal orderPortal) {

    }

}
