package com.liaoyb.order.service;

import com.liaoyb.order.model.OrderDetail;
import com.liaoyb.order.model.OrderMaster;

import java.util.List; /**
 * @author liaoyb
 */
public interface OrderService {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);

    OrderMaster saveOrderMaster(OrderMaster orderMaster);

    void saveOrderDetails(List<OrderDetail> orderDetails);
}
