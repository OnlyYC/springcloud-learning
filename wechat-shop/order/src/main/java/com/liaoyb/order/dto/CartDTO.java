package com.liaoyb.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liaoyb
 */
@AllArgsConstructor
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
