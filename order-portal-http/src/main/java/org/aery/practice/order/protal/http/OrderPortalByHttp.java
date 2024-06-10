package org.aery.practice.order.protal.http;

import org.aery.practice.order.portal.api.OrderPortal;
import org.aery.practice.order.portal.api.OrderPortalInfo;
import org.aery.practice.order.service.api.OrderService;
import org.aery.practice.order.utils.vo.OrderInfo;
import org.aery.practice.order.utils.vo.TradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderPortalByHttp implements OrderPortal {

    public static void main(String[] args) {
        SpringApplication.run(OrderPortalByHttp.class, args);
    }

    private final OrderPortalInfo info = new OrderPortalInfo("order-portal-http");

    @Autowired
    private OrderService orderService;

    @Override
    public OrderPortalInfo getInfo() {
        return this.info;
    }

    @Override
    public OrderInfo createOrder(String userId, TradeInfo tradeInfo) {
        return this.orderService.createOrder(userId, tradeInfo);
    }

}
