package org.aery.practice.order.service.core;

import org.aery.practice.order.service.api.OrderService;
import org.aery.practice.order.service.api.OrderServiceInfo;
import org.aery.practice.order.utils.vo.OrderInfo;
import org.aery.practice.order.utils.vo.TradeInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceByCore implements OrderService {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceByCore.class, args);
    }

    private final OrderServiceInfo info = new OrderServiceInfo("service-core");

    @Override
    public OrderServiceInfo getInfo() {
        return this.info;
    }

    @Override
    public OrderInfo createOrder(String userId, TradeInfo tradeInfo) {
        String orderId = "order-" + System.currentTimeMillis();
        return new OrderInfo(orderId, tradeInfo);
    }

}
