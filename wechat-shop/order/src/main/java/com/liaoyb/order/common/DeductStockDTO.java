package com.liaoyb.order.common;

import lombok.Data;

/**
 * @author liaoyb
 */
@Data
public class DeductStockDTO {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 数量
     */
    private Long productQuantity;
}
