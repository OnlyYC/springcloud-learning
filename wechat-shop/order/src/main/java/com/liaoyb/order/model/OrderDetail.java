package com.liaoyb.order.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liaoyb
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;

    private String orderId;

    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 当前价格,单位分
     */
    private BigDecimal productPrice;
    /**
     * 数量
     */
    private Long productQuantity;
    /**
     * 小图
     */
    private String productIcon;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
