import org.aery.practice.order.portal.api.PortalLoader;
import org.aery.practice.order.protal.http.HttpPortalLoader;

open module aery.practice.order.portal.http {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires transitive aery.practice.order.portal.api;

    provides PortalLoader with HttpPortalLoader;

}
