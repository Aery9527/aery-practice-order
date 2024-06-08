import org.aery.practice.order.core.OrderMatcherAdapter;

open module aery.practice.order.core {

    requires transitive aery.practice.order.api;
    requires aery.practice.order.utils;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires misty.utils;

    provides org.aery.practice.order.api.OrderMatcher with OrderMatcherAdapter;

}
