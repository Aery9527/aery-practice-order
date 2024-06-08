package org.aery.practice.order.core;

import org.aery.practice.order.api.OrderMatcher;
import org.aery.practice.order.api.data.OrderMatcherInfo;
import org.aery.practice.order.utils.error.GlobalException;
import org.aery.practice.order.utils.error.UtilsVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderMatcherByCore implements OrderMatcher {

    public static void main(String[] args) {
        SpringApplication.run(OrderMatcherByCore.class, args);
    }

    @Autowired
    private OrderMatcherInfoByCore info;

    @Autowired
    private UtilsVerifier verifier;

    @Override
    public OrderMatcherInfo getInfo() {
        return this.info;
    }

}
