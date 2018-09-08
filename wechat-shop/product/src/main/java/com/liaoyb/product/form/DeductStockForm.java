package com.liaoyb.product.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 扣库存表单
 *
 * @author liaoyb
 */
@Data
public class DeductStockForm {
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
