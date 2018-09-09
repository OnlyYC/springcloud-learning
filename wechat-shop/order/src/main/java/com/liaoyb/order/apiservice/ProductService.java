package com.liaoyb.order.apiservice;

import com.liaoyb.order.common.ProductInfoDTO;
import com.liaoyb.order.common.Result;
import com.liaoyb.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author liaoyb
 */
@FeignClient(value = "product")
public interface ProductService {
    /***
     * 根据商品编号获取商品信息
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/product/findByIdList")
    Result<List<ProductInfoDTO>> getProducts(List<String> productIdList);

    /**
     * 扣库存
     *
     * @param cartDTOList
     * @return
     */
    @PutMapping("/product/decreasesStock")
    Result decreasesStock(List<CartDTO> cartDTOList);
}
