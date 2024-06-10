package org.aery.practice.order.portal.api;

import org.aery.practice.order.utils.Component;
import org.aery.practice.order.utils.vo.OrderInfo;
import org.aery.practice.order.utils.vo.TradeInfo;

public interface OrderPortal extends Component<OrderPortalInfo> {

    OrderInfo createOrder(String userId, TradeInfo tradeInfo);

//    boolean cancelOrder(String userId, String orderId);
//
//    List<OrderInfo> queryOrder(String userId, LocalDate start, LocalDate end, OrderState... states);
//
//    List<OrderInfo> queryOrderWithPrice(String userId, LocalDate start, LocalDate end, BigDecimal from, BigDecimal to, OrderState... states);
//
//    List<OrderInfo> queryOrderWithVolume(String userId, LocalDate start, LocalDate end, BigDecimal from, BigDecimal to, OrderState... states);
//
//    List<OrderInfo> queryOrderWithDetail(String userId, LocalDate start, LocalDate end, TradeDetail from, TradeDetail to, OrderState... states);

}
