package com.liaoyb.order.service.impl;

import com.liaoyb.order.model.OrderDetail;
import com.liaoyb.order.model.OrderMaster;
import com.liaoyb.order.repository.OrderDetailRepository;
import com.liaoyb.order.repository.OrderMasterRepository;
import com.liaoyb.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaoyb
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderMaster saveOrderMaster(OrderMaster orderMaster) {
        return orderMasterRepository.save(orderMaster);
    }

    @Override
    public void saveOrderDetails(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }
}
