import org.aery.practice.order.scanner.api.ScannerLoader;
import org.aery.practice.order.scanner.core.CoreScannerLoader;

open module aery.practice.order.scanner.core {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires transitive aery.practice.order.scanner.api;

    provides ScannerLoader with CoreScannerLoader;

}
