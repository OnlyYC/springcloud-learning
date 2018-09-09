package com.liaoyb.product.controller;

import com.liaoyb.product.common.Result;
import com.liaoyb.product.dto.CartDTO;
import com.liaoyb.product.form.DeductStockForm;
import com.liaoyb.product.model.ProductCategory;
import com.liaoyb.product.model.ProductInfo;
import com.liaoyb.product.service.ProductService;
import com.liaoyb.product.vo.ProductRankVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liaoyb
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 商品列表
     *
     * @return
     */
    @GetMapping("/product/list")
    public Result<List<ProductRankVO>> getProductRank() {
        //获取所有商品
        List<ProductInfo> productInfos = productService.getProductList();

        //获取商品类别
        List<ProductCategory> productCategories = productService.getProductCategoryList();

        List<ProductRankVO> productRankVOList = new ArrayList<>();

        //遍历转换数据
        Map<Integer, List<ProductInfo>> productsByTypeMap = productInfos.stream().collect(Collectors.groupingBy(ProductInfo::getCategoryType));
        for (ProductCategory productCategory : productCategories) {
            ProductRankVO productRankVO = new ProductRankVO();
            productRankVO.setCategoryName(productCategory.getCategoryName());
            productRankVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfo> productInfoListByType = productsByTypeMap.get(productCategory.getCategoryType());
            if (!CollectionUtils.isEmpty(productInfoListByType)) {
                List<ProductRankVO.ProductVO> productVOList = productInfoListByType
                        .stream().map(new Function<ProductInfo, ProductRankVO.ProductVO>() {
                            @Override
                            public ProductRankVO.ProductVO apply(ProductInfo productInfo) {
                                ProductRankVO.ProductVO productVO = new ProductRankVO.ProductVO();
                                BeanUtils.copyProperties(productInfo, productVO);
                                return productVO;
                            }
                        }).collect(Collectors.toList());
                productRankVO.setProducts(productVOList);
            }
            productRankVOList.add(productRankVO);
        }


        return Result.success(productRankVOList);
    }


    /**
     * 根据商品编号查询商品信息
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/product/findByIdList")
    public Result<List<ProductInfo>> getProducts(@RequestBody List<String> productIdList) {
        Assert.notEmpty(productIdList, "商品编号不能为空");
        return Result.success(productService.getProducts(productIdList));
    }

    /**
     * 扣库存
     *
     * @param cartDTOList
     * @return
     */
    @PutMapping("/product/decreasesStock")
    public Result decreasesStock(@Valid @RequestBody List<CartDTO> cartDTOList) {
        try {
            productService.decreasesStock(cartDTOList);
            return Result.success(null);
        }catch (Exception e){
            return Result.fail(-1, "库存不足");
        }
    }

}
