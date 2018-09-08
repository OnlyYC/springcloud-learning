package com.liaoyb.order.common;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liaoyb
 */
@Data
public class ProductInfoDTO {
    private String productId;
    /**
     * 商品名
     */
    private String productName;

    /**
     * 价格
     */
    private BigDecimal productPrice;
    /**
     * 库存
     */
    private Long productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 图片
     */
    private String productIcon;
    /**
     * 商品状态，0正常 1下架
     */
    private Integer productStatus;
    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
