package org.aery.practice.order.scanner.core;

import org.aery.practice.order.scanner.api.Scanner;
import org.aery.practice.order.scanner.api.ScannerInfo;

public class CoreScanner implements Scanner {

    private final ScannerInfo info = new ScannerInfo("core-scanner");

    @Override
    public ScannerInfo getInfo() {
        return this.info;
    }

}
