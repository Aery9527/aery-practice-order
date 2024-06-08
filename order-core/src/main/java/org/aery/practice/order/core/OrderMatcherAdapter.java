package org.aery.practice.order.core;

import org.aery.practice.order.api.OrderMatcher;
import org.aery.practice.order.api.data.OrderMatcherInfo;
import org.aery.practice.order.utils.error.ErrorCode;
import org.aery.practice.order.utils.error.GlobalException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 用來轉接 order-portal 使用 ServiceLoader 載入的 {@link OrderMatcher} 實作,
 * 目的是讓 {@link OrderMatcherByCore} 保持單純
 */
public class OrderMatcherAdapter implements OrderMatcher {

    public ConfigurableApplicationContext springContext;

    private OrderMatcher orderMatcher;

    @Override
    public void start(String... args) throws GlobalException {
        if (this.springContext == null) {
            this.springContext = SpringApplication.run(OrderMatcherByCore.class, args);
            this.orderMatcher = this.springContext.getBean(OrderMatcher.class);
        } else {
            ErrorCode.INCORRECT_STATE.thrown("already started");
        }
    }

    @Override
    public void stop() throws GlobalException {
        if (this.springContext == null) {
            ErrorCode.INCORRECT_STATE.thrown("not started yet");

        } else {
            this.springContext.close();

            this.springContext = null;
            this.orderMatcher = null;
        }
    }

    @Override
    public OrderMatcherInfo getInfo() {
        return this.orderMatcher.getInfo();
    }

}
