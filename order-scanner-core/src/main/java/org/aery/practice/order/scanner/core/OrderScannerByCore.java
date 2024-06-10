package org.aery.practice.order.scanner.core;

import org.aery.practice.order.scanner.api.OrderScanner;
import org.aery.practice.order.scanner.api.OrderScannerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderScannerByCore implements OrderScanner {

    public static void main(String[] args) {
        SpringApplication.run(OrderScannerByCore.class, args);
    }

    private final OrderScannerInfo info = new OrderScannerInfo("order-scanner-core");

    @Override
    public OrderScannerInfo getInfo() {
        return this.info;
    }

}
