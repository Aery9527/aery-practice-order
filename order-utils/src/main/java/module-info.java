open module aery.practice.order.utils {

    requires spring.context;
    requires spring.boot;

    requires misty.utils;

    exports org.aery.practice.order.utils;
    exports org.aery.practice.order.utils.error;
    exports org.aery.practice.order.utils.bean;
    exports org.aery.practice.order.utils.config;

}
