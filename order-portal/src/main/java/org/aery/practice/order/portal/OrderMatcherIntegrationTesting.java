package org.aery.practice.order.portal;

import org.aery.practice.order.api.OrderMatcher;

public class OrderMatcherIntegrationTesting {

    public static void main(String[] args) {
        OrderMatcher orderMatcher1 = OrderMatcher.load();
        orderMatcher1.start(args);
    }

}
