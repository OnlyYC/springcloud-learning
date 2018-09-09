package com.liaoyb.product.client;

import com.liaoyb.product.common.DecreaseStockInput;
import com.liaoyb.product.common.ProductInfoOutput;
import com.liaoyb.product.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author liaoyb
 */
@FeignClient(value = "product")
public interface ProductClient {
    /***
     * 根据商品编号获取商品信息
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/product/findByIdList")
    Result<List<ProductInfoOutput>> getProducts(List<String> productIdList);

    /**
     * 扣库存
     *
     * @param cartDTOList
     * @return
     */
    @PutMapping("/product/decreasesStock")
    Result decreasesStock(List<DecreaseStockInput> cartDTOList);
}
