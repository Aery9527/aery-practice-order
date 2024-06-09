import org.aery.practice.order.scanner.api.ScannerLoader;

module aery.practice.order.scanner.api {

    requires aery.practice.order.utils;

    exports org.aery.practice.order.scanner.api;

    uses ScannerLoader;

}
