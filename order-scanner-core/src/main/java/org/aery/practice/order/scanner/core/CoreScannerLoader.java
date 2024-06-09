package org.aery.practice.order.scanner.core;

import org.aery.practice.order.scanner.api.Scanner;
import org.aery.practice.order.scanner.api.ScannerInfo;
import org.aery.practice.order.scanner.api.ScannerLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreScannerLoader extends ComponentLoaderAbstract<Scanner, ScannerInfo> implements ScannerLoader {

    public static void main(String[] args) {
        new CoreScannerLoader().start(args);
    }

    @Override
    public Class<? extends Scanner> getTargetType() {
        return CoreScanner.class;
    }

}
