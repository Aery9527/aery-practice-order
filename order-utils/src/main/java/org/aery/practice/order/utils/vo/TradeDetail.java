package org.aery.practice.order.utils.vo;

import java.math.BigDecimal;

/**
 * 交易明細
 *
 * @param price  交易價格
 * @param volume 交易數量
 */
public record TradeDetail(
        BigDecimal price,
        BigDecimal volume
) {
}
