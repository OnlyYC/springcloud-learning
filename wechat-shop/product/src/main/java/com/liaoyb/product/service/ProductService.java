package com.liaoyb.product.service;

import com.liaoyb.product.model.ProductCategory;
import com.liaoyb.product.model.ProductInfo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liaoyb
 */
public interface ProductService {
    List<ProductInfo> getProductList();
    List<ProductCategory> getProductCategoryList();

    List<ProductInfo> getProducts(List<String> productIdList);

    int deductStock(String productId, Long productQuantity);
}
