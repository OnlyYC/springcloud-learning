package com.liaoyb.product.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liaoyb
 */
@Data
public class CartDTO {
    /**
     * 商品id
     */
    @NotNull
    private String productId;
    /**
     * 数量
     */
    @NotNull
    private Long productQuantity;
}
