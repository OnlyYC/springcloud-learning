package com.liaoyb.product.service;

import com.liaoyb.product.dto.CartDTO;
import com.liaoyb.product.model.ProductCategory;
import com.liaoyb.product.model.ProductInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liaoyb
 */
public interface ProductService {
    List<ProductInfo> getProductList();
    List<ProductCategory> getProductCategoryList();

    List<ProductInfo> getProducts(List<String> productIdList);

    void decreasesStock(@Valid List<CartDTO> cartDTOList);
}
