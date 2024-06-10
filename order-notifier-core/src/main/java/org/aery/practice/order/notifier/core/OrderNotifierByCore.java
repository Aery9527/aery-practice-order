package org.aery.practice.order.notifier.core;

import org.aery.practice.order.notifier.api.OrderNotifier;
import org.aery.practice.order.notifier.api.OrderNotifierInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderNotifierByCore implements OrderNotifier {

    public static void main(String[] args) {
        SpringApplication.run(OrderNotifierByCore.class, args);
    }

    private final OrderNotifierInfo info = new OrderNotifierInfo("order-notifier-core");

    @Override
    public OrderNotifierInfo getInfo() {
        return this.info;
    }

}
