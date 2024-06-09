import org.aery.practice.order.core.OrderMatcherAdapter;

open module aery.practice.order.core {

    requires transitive aery.practice.order.api;
    requires aery.practice.order.utils;
//    requires spring.core; // 自動模組, 不引入的話會無法執行, 因為 AOP 會反向從這個模組要操作到這個 module, 拿掉的話會看到無法存取無名模組的訊息
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires misty.utils;

    provides org.aery.practice.order.api.OrderMatcher with OrderMatcherAdapter;

}
