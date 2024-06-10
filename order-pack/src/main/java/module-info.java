module aery.practice.order.pack {

    requires spring.boot;
    requires spring.context;

    requires aery.practice.order.matcher.api;
    requires aery.practice.order.portal.api;
    requires aery.practice.order.scanner.api;
    requires aery.practice.order.notifier.api;
    requires aery.practice.order.utils;

}
