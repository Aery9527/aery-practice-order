package org.aery.practice.order.portal;

import org.aery.practice.order.api.OrderMatcher;

/**
 * --add-reads aery.practice.order.core=ALL-UNNAMED
 * --module-path mods:libs
 */
public class OrderMatcherIntegrationTesting {

    public static void main(String[] args) {
        Module u = ClassLoader.getPlatformClassLoader().getUnnamedModule();
        OrderMatcher orderMatcher1 = org.aery.practice.order.api.OrderMatcher.load();
        orderMatcher1.start(args);
    }

}
