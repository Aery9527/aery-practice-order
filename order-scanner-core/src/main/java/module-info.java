import org.aery.practice.order.scanner.api.OrderScannerLoader;
import org.aery.practice.order.scanner.core.OrderScannerByCoreLoader;

open module aery.practice.order.scanner.core {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires transitive aery.practice.order.scanner.api;

    provides OrderScannerLoader with OrderScannerByCoreLoader;

}
