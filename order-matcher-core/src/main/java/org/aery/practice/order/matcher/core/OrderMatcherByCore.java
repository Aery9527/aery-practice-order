package org.aery.practice.order.matcher.core;

import org.aery.practice.order.matcher.api.OrderMatcher;
import org.aery.practice.order.matcher.api.OrderMatcherInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderMatcherByCore implements OrderMatcher {

    public static void main(String[] args) {
        SpringApplication.run(OrderMatcherByCore.class, args);
    }

    private final OrderMatcherInfo info = new OrderMatcherInfo("order-matcher-core");

    @Override
    public OrderMatcherInfo getInfo() {
        return this.info;
    }

}
