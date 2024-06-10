package org.aery.practice.order.scanner.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface OrderScannerLoader extends ComponentLoader<OrderScanner, OrderScannerInfo> {

    static OrderScannerLoader create() {
        ServiceLoader<OrderScannerLoader> serviceLoader = ServiceLoader.load(OrderScannerLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
