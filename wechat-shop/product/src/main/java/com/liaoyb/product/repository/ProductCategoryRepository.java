package com.liaoyb.product.repository;

import com.liaoyb.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liaoyb
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
