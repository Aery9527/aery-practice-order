module aery.practice.order.api {
    requires aery.practice.order.utils;

    exports org.aery.practice.order.api;
    exports org.aery.practice.order.api.data;

    uses org.aery.practice.order.api.OrderMatcher;

}
