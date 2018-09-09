package com.liaoyb.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liaoyb
 */
@Data
public class ProductRankVO {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductVO> products;

    @Data
    public static class ProductVO{
        @JsonProperty("id")
        private String productId;

        @JsonProperty("name")
        private String productName;

        @JsonProperty("price")
        private BigDecimal productPrice;

        @JsonProperty("description")
        private String productDescription;

        @JsonProperty("icon")
        private String productIcon;
    }
}
