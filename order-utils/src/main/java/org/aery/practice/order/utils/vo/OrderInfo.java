package org.aery.practice.order.utils.vo;

/**
 * 訂單資訊
 *
 * @param orderId   訂單編號
 * @param tradeInfo 交易資訊
 */
public record OrderInfo(
        String orderId,
        TradeInfo tradeInfo
) {
}
