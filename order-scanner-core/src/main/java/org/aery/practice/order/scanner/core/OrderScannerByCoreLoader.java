package org.aery.practice.order.scanner.core;

import org.aery.practice.order.scanner.api.OrderScanner;
import org.aery.practice.order.scanner.api.OrderScannerInfo;
import org.aery.practice.order.scanner.api.OrderScannerLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class OrderScannerByCoreLoader extends ComponentLoaderAbstract<OrderScanner, OrderScannerInfo> implements OrderScannerLoader {

    @Override
    public Class<? extends OrderScanner> getCoreType() {
        return OrderScannerByCore.class;
    }

}
