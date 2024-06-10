import org.aery.practice.order.portal.api.OrderPortalLoader;

module aery.practice.order.portal.api {

    requires aery.practice.order.utils;

    exports org.aery.practice.order.portal.api;

    uses OrderPortalLoader;

}
