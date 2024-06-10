import org.aery.practice.order.matcher.api.OrderMatcherLoader;
import org.aery.practice.order.matcher.core.OrderMatcherByCoreLoader;

open module aery.practice.order.matcher.core {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires transitive aery.practice.order.matcher.api;

    provides OrderMatcherLoader with OrderMatcherByCoreLoader;

}
