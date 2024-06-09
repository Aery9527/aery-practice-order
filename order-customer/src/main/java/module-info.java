open module aery.practice.order.customer {

    requires spring.boot.autoconfigure;
    requires spring.core;

    requires misty.utils;
    requires aery.practice.order.portal.api;
    requires aery.practice.order.utils;

    exports org.aery.practice.order.customer;

}
