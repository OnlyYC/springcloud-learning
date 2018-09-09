package com.liaoyb.order.apiservice;

import com.google.common.collect.Lists;
import com.liaoyb.order.OrderApplicationTests;
import com.liaoyb.order.common.ProductInfoDTO;
import com.liaoyb.order.common.Result;
import com.liaoyb.order.dto.CartDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author liaoyb
 */
public class ProductServiceTest extends OrderApplicationTests {
    @Autowired
    private ProductService productService;

    @Test
    public void testGetProducts() {
        Result<List<ProductInfoDTO>> productInfoDTOResult = productService.getProducts(Lists.newArrayList("164103465734242707"));
        Assert.assertTrue(productInfoDTOResult.isSuccess());
        Assert.assertTrue(productInfoDTOResult.getData().size() > 0);
    }

    @Test
    public void testDeductStock() {
        List<CartDTO> cartDTOList = Lists.newArrayList();
        cartDTOList.add(new CartDTO("157875196366160022", 2L));
        cartDTOList.add(new CartDTO("157875227953464068", 2L));
        Result result = productService.decreasesStock(cartDTOList);
        Assert.assertTrue(result.isSuccess());
    }

}
