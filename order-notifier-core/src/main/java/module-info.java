import org.aery.practice.order.notifier.api.OrderNotifierLoader;
import org.aery.practice.order.notifier.core.OrderNotifierLoaderByCore;

open module aery.practice.order.notifier.core {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires transitive aery.practice.order.notifier.api;

    provides OrderNotifierLoader with OrderNotifierLoaderByCore;

}
