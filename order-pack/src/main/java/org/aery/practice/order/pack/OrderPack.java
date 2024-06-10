package org.aery.practice.order.pack;

import org.aery.practice.order.matcher.api.OrderMatcher;
import org.aery.practice.order.matcher.api.OrderMatcherLoader;
import org.aery.practice.order.notifier.api.OrderNotifier;
import org.aery.practice.order.notifier.api.OrderNotifierLoader;
import org.aery.practice.order.portal.api.OrderPortal;
import org.aery.practice.order.portal.api.OrderPortalLoader;
import org.aery.practice.order.scanner.api.OrderScanner;
import org.aery.practice.order.scanner.api.OrderScannerLoader;
import org.aery.practice.order.utils.ComponentLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class OrderPack {

    public static void main(String[] args) {
        OrderMatcherLoader orderMatcherLoader = OrderMatcherLoader.create();
        OrderPortalLoader orderPortalLoader = OrderPortalLoader.create();
        OrderScannerLoader orderScannerLoader = OrderScannerLoader.create();
        OrderNotifierLoader orderNotifierLoader = OrderNotifierLoader.create();

        Class<?>[] allResources = ComponentLoader.mergeSource(
                orderMatcherLoader,
                orderPortalLoader,
                orderScannerLoader,
                orderNotifierLoader
        );

        ConfigurableApplicationContext sprintContext = SpringApplication.run(allResources, args);
        OrderMatcher orderMatcher = sprintContext.getBean(orderMatcherLoader.getCoreType());
        OrderPortal orderPortal = sprintContext.getBean(orderPortalLoader.getCoreType());
        OrderScanner orderScanner = sprintContext.getBean(orderScannerLoader.getCoreType());
        OrderNotifier orderNotifier = sprintContext.getBean(orderNotifierLoader.getCoreType());

        System.out.println("OrderMatcher  load : " + orderMatcher.getInfo().name());
        System.out.println("OrderPortal   load : " + orderPortal.getInfo().name());
        System.out.println("OrderScanner  load : " + orderScanner.getInfo().name());
        System.out.println("OrderNotifier load : " + orderNotifier.getInfo().name());

        sprintContext.stop();
    }

}
