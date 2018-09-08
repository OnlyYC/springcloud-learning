package com.liaoyb.order.repository;

import com.liaoyb.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liaoyb
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

}
