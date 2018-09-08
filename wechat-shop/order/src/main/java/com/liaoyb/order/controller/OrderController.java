package com.liaoyb.order.controller;

import com.liaoyb.order.apiservice.ProductService;
import com.liaoyb.order.common.DeductStockDTO;
import com.liaoyb.order.common.ProductInfoDTO;
import com.liaoyb.order.common.Result;
import com.liaoyb.order.common.utils.IdGenerator;
import com.liaoyb.order.dto.OrderResultDTO;
import com.liaoyb.order.form.OrderForm;
import com.liaoyb.order.model.OrderDetail;
import com.liaoyb.order.model.OrderMaster;
import com.liaoyb.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 订单
 *
 * @author liaoyb
 */
@RestController
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    @Transactional
    public Result<OrderResultDTO> createOrder(@Valid @RequestBody OrderForm orderForm) {
        List<String> productIdList = orderForm.getItems().stream().map(OrderForm.OrderProductItem::getProductId).collect(Collectors.toList());
        //通过订单的表单查询商品信息
        String[] productIds = new String[productIdList.size()];
        productIds = productIdList.toArray(productIds);
        Result<List<ProductInfoDTO>> productsResult = productService.getProducts(productIds);

        Map<String, List<ProductInfoDTO>> productsByIdMap = productsResult.getData().stream().collect(Collectors.groupingBy(ProductInfoDTO::getProductId));


        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(IdGenerator.generate());
        orderMaster.setBuyerName(orderForm.getName());
        orderMaster.setBuyerPhone(orderForm.getPhone());
        orderMaster.setBuyerAddress(orderForm.getAddress());
        orderMaster.setBuyerOpenid(orderForm.getOpenid());
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        BigDecimal totalAmount = new BigDecimal(0);


        //
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderForm.OrderProductItem orderProductItem : orderForm.getItems()) {
            List<ProductInfoDTO> productInfoDTOs = productsByIdMap.get(orderProductItem.getProductId());
            Assert.notEmpty(productInfoDTOs, "商品:" + orderProductItem.getProductId() + "不存在");
            Assert.isTrue(orderProductItem.getProductQuantity() > 0, "购买商品数量需大于0");
            ProductInfoDTO productInfoDTO = productInfoDTOs.get(0);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(IdGenerator.generate());
            orderDetail.setOrderId(orderMaster.getOrderId());
            orderDetail.setProductId(productInfoDTO.getProductId());
            orderDetail.setProductName(productInfoDTO.getProductName());
            orderDetail.setProductPrice(productInfoDTO.getProductPrice());
            orderDetail.setProductQuantity(orderProductItem.getProductQuantity());
            orderDetail.setProductIcon(productInfoDTO.getProductIcon());
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());
            //扣库存
            DeductStockDTO deductStockDTO = new DeductStockDTO();
            deductStockDTO.setProductId(orderDetail.getProductId());
            deductStockDTO.setProductQuantity(orderDetail.getProductQuantity());
            Result result = productService.deductStock(deductStockDTO);

            if(!result.isSuccess()){
                //库存扣取失败
                throw new RuntimeException("商品:"+orderDetail.getProductId()+"扣取库存失败,原因:"+result.getMsg());
            }
            orderDetails.add(orderDetail);


            totalAmount = totalAmount.add(
                    orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
            );
        }
        //todo 其中一个商品库存扣取失败，事物如何补偿

        orderMaster.setOrderAmount(totalAmount);
        //保存订单商品
        orderService.saveOrderDetails(orderDetails);

        //保存订单
        orderService.saveOrderMaster(orderMaster);
        return Result.success(new OrderResultDTO(orderMaster.getOrderId()));
    }
}
