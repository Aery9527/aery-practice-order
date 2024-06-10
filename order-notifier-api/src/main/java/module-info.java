import org.aery.practice.order.notifier.api.OrderNotifierLoader;

module aery.practice.order.notifier.api {

    requires aery.practice.order.utils;

    exports org.aery.practice.order.notifier.api;

    uses OrderNotifierLoader;

}
