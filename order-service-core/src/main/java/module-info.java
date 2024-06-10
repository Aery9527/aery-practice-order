import org.aery.practice.order.service.api.OrderServiceLoader;
import org.aery.practice.order.service.core.OrderServiceByCoreLoader;

open module aery.practice.order.service.core {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires transitive aery.practice.order.service.api;

    provides OrderServiceLoader with OrderServiceByCoreLoader;

}
