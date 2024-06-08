package org.aery.practice.order.core;

import org.aery.practice.order.api.data.OrderMatcherInfo;
import org.springframework.stereotype.Component;

@Component
public class OrderMatcherInfoByCore implements OrderMatcherInfo {

    @Override
    public String getName() {
        return "core-preset";
    }

}
