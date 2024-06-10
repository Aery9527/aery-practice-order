package org.aery.practice.order.utils.vo;

/**
 * 交易資訊
 * <p>
 * "買/賣"(A) "限價/市價"(B) 拆成兩個欄位,
 * 現階段看來沒啥問題,
 * 但如果 A 出現新的類型, 但此類型並沒有 B 的 "限價/市價" 搭配,
 * 那這樣未來檢查會越寫越多,
 * 所以本來想說設計成單一欄位就好避免這問題,
 * 不過後來想想, 還是可以將兩個值串成一個值比對白名單即可,
 * 這樣拆成兩個蘭為方便程式操作,
 * 也可節省未來檢查邏輯的增長.
 *
 * @param tradeTarget 交易目標, 美金/比特幣/以太幣/...
 * @param tradeType   交易類型, 買/賣...
 * @param tradeMode  交易方式, 限價/市價/...
 * @param detail      交易明細
 */
public record TradeInfo(
        String tradeTarget,
        String tradeType,
        String tradeMode,
        TradeDetail detail
) {
}
