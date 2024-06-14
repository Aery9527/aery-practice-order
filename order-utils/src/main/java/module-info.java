open module aery.practice.order.utils {

    requires jakarta.annotation;

    requires misty.utils;
    requires org.slf4j;
    requires spring.context;
    requires spring.boot;
    requires spring.beans;
    requires spring.core;
    requires spring.data.redis;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.databind;

    exports org.aery.practice.order.utils;
    exports org.aery.practice.order.utils.vo;
    exports org.aery.practice.order.utils.error;

    exports org.aery.practice.order.utils.config;
    exports org.aery.practice.order.utils.config.auto;
    exports org.aery.practice.order.utils.bean.impl;
    exports org.aery.practice.order.utils.locate.api;
    exports org.aery.practice.order.utils.mark.conditional;

}
