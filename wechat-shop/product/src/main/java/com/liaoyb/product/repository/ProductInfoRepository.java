package com.liaoyb.product.repository;

import com.liaoyb.product.model.ProductInfo;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author liaoyb
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ProductInfo pi set pi.productStock = pi.productStock-?2 where pi.productStock>=?2 and pi.productId =?1")
    int deductStock(String productId, Long productQuantity);
}
