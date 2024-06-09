import org.aery.practice.order.portal.api.PortalLoader;
import org.aery.practice.order.portal.core.CorePortalLoader;

open module aery.practice.order.portal.core {

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires misty.utils;
    requires aery.practice.order.utils;
    requires transitive aery.practice.order.portal.api;

    provides PortalLoader with CorePortalLoader;

}
