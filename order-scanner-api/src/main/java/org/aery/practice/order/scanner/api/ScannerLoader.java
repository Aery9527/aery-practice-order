package org.aery.practice.order.scanner.api;

import org.aery.practice.order.utils.ComponentLoader;
import org.aery.practice.order.utils.ServiceLoaderBySingleton;

import java.util.ServiceLoader;

public interface ScannerLoader extends ComponentLoader<Scanner, ScannerInfo> {

    static ScannerLoader create() {
        ServiceLoader<ScannerLoader> serviceLoader = ServiceLoader.load(ScannerLoader.class);
        return ServiceLoaderBySingleton.load(serviceLoader);
    }

}
