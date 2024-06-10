package org.aery.practice.order.protal.http;

import org.aery.practice.order.portal.api.OrderPortal;
import org.aery.practice.order.portal.api.OrderPortalInfo;
import org.aery.practice.order.portal.api.OrderPortalLoader;
import org.aery.practice.order.utils.ComponentLoaderAbstract;

public class OrderPortalByHttpLoader extends ComponentLoaderAbstract<OrderPortal, OrderPortalInfo> implements OrderPortalLoader {

    @Override
    public Class<? extends OrderPortalByHttp> getCoreType() {
        return OrderPortalByHttp.class;
    }

}
