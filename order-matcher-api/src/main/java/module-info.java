import org.aery.practice.order.matcher.api.OrderMatcherLoader;

module aery.practice.order.matcher.api {

    requires aery.practice.order.utils;

    exports org.aery.practice.order.matcher.api;

    uses OrderMatcherLoader;

}
