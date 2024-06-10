package org.aery.practice.order.service.api;

import org.aery.practice.order.utils.Component;
import org.aery.practice.order.utils.vo.OrderInfo;
import org.aery.practice.order.utils.vo.TradeInfo;

public interface OrderService extends Component<OrderServiceInfo> {

    OrderInfo createOrder(String userId, TradeInfo tradeInfo);

}
