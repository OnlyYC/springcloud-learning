package com.liaoyb.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liaoyb
 */
@AllArgsConstructor
@Data
public class DecreaseStockInput {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 数量
     */
    private Long productQuantity;
}
