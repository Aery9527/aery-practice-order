import org.aery.practice.order.service.api.OrderServiceLoader;

module aery.practice.order.service.api {

    requires aery.practice.order.utils;

    exports org.aery.practice.order.service.api;

    uses OrderServiceLoader;

}
