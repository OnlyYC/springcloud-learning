package com.liaoyb.order.apiservice;

import com.liaoyb.order.OrderApplicationTests;
import com.liaoyb.order.common.DeductStockDTO;
import com.liaoyb.order.common.ProductInfoDTO;
import com.liaoyb.order.common.Result;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author liaoyb
 */
public class ProductServiceTest extends OrderApplicationTests {
//    @GetMapping("/product/findByIdList")
//    Result<ProductInfoDTO> getProducts(String[] productIdList);
//
//    @PutMapping("/product/deductStock")
//    Result deductStock(DeductStockDTO deductStockDTO);

    @Autowired
    private ProductService productService;

    @Test
    public void testGetProducts() {
        Result<List<ProductInfoDTO>> productInfoDTOResult = productService.getProducts(new String[]{"164103465734242707"});
        Assert.assertTrue(productInfoDTOResult.isSuccess());
        Assert.assertTrue(productInfoDTOResult.getData().size() > 0);

    }

    @Test
    public void testDeductStock(){
        DeductStockDTO deductStockDTO = new DeductStockDTO();
        deductStockDTO.setProductId("157875196366160022");
        deductStockDTO.setProductQuantity(2L);
        Result result = productService.deductStock(deductStockDTO);
        Assert.assertTrue(result.isSuccess());
    }

}
