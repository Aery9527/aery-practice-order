import org.aery.practice.order.portal.api.OrderPortalLoader;
import org.aery.practice.order.protal.http.OrderPortalByHttpLoader;

open module aery.practice.order.portal.http {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires aery.practice.order.service.api;
    requires transitive aery.practice.order.portal.api;

    provides OrderPortalLoader with OrderPortalByHttpLoader;

}
