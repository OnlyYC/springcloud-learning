package com.liaoyb.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liaoyb
 */
@Data
public class OrderForm {
    private String name;
    private String phone;
    private String address;
    @NotNull
    private String openid;

    @NotEmpty
    private List<OrderProductItem> items;

    @Data
    public static class OrderProductItem {
        /**
         * 商品id
         */
        private String productId;
        /**
         * 购买数量
         */
        private Long productQuantity;
    }
}
