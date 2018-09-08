package com.liaoyb.order.apiservice;

import com.liaoyb.order.common.DeductStockDTO;
import com.liaoyb.order.common.ProductInfoDTO;
import com.liaoyb.order.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author liaoyb
 */
@FeignClient(value = "product")
public interface ProductService {
    @PostMapping("/product/findByIdList")
    Result<List<ProductInfoDTO>> getProducts(@RequestParam("productIdList") String[] productIdList);

    @PutMapping("/product/deductStock")
    Result deductStock(DeductStockDTO deductStockDTO);
}
