package org.aery.practice.order.test;

import org.aery.practice.order.customer.OrderCustomer;
import org.aery.practice.order.customer.OrderCustomerLoader;
import org.aery.practice.order.matcher.api.OrderMatcher;
import org.aery.practice.order.matcher.api.OrderMatcherLoader;
import org.aery.practice.order.notifier.api.OrderNotifier;
import org.aery.practice.order.notifier.api.OrderNotifierLoader;
import org.aery.practice.order.portal.api.OrderPortal;
import org.aery.practice.order.portal.api.OrderPortalLoader;
import org.aery.practice.order.scanner.api.OrderScanner;
import org.aery.practice.order.scanner.api.OrderScannerLoader;

public class IntegrationTesting {

    public static void main(String[] args) {
        OrderMatcherLoader orderMatcherLoader = OrderMatcherLoader.create();
        OrderMatcher orderMatcher = orderMatcherLoader.start(args);

        OrderPortalLoader orderPortalLoader = OrderPortalLoader.create();
        OrderPortal orderPortal = orderPortalLoader.start(args);

        OrderScannerLoader orderScannerLoader = OrderScannerLoader.create();
        OrderScanner orderScanner = orderScannerLoader.start(args);

        OrderNotifierLoader orderNotifierLoader = OrderNotifierLoader.create();
        OrderNotifier orderNotifier = orderNotifierLoader.start(args);

        OrderCustomerLoader orderCustomerLoader = OrderCustomerLoader.create();
        OrderCustomer orderCustomer = orderCustomerLoader.start(args);

        orderCustomer.simulate(orderPortal); // 開始模擬客戶送出買賣訂單

        System.out.println("OrderMatcher  : " + orderMatcher.getInfo().name());
        System.out.println("OrderPortal   : " + orderPortal.getInfo().name());
        System.out.println("OrderScanner  : " + orderScanner.getInfo().name());
        System.out.println("OrderNotifier : " + orderNotifier.getInfo().name());
        System.out.println("OrderCustomer : " + orderCustomer.getInfo().name());

        orderMatcherLoader.stop();
        orderPortalLoader.stop();
        orderScannerLoader.stop();
        orderNotifierLoader.stop();
        orderCustomerLoader.stop();
    }

}
