package com.liaoyb.product.service.impl;

import com.liaoyb.product.dto.CartDTO;
import com.liaoyb.product.model.ProductCategory;
import com.liaoyb.product.model.ProductInfo;
import com.liaoyb.product.repository.ProductCategoryRepository;
import com.liaoyb.product.repository.ProductInfoRepository;
import com.liaoyb.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

/**
 * @author liaoyb
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductInfo> getProductList() {
        return productInfoRepository.findAll();
    }

    @Override
    public List<ProductCategory> getProductCategoryList() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductInfo> getProducts(List<String> productIdList) {
        return productInfoRepository.findAllById(productIdList);
    }

    @Override
    @Transactional
    public void decreasesStock(@Valid List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            int affectRow = productInfoRepository.deductStock(cartDTO.getProductId(), cartDTO.getProductQuantity());
            if(affectRow == 0){
                throw new RuntimeException("商品:"+cartDTO.getProductId()+"库存不足");
            }
        }
    }
}
