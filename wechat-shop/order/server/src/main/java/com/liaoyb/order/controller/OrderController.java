package com.liaoyb.order.controller;

import com.liaoyb.order.common.Result;
import com.liaoyb.order.common.utils.IdGenerator;
import com.liaoyb.order.dto.OrderResultDTO;
import com.liaoyb.order.form.OrderForm;
import com.liaoyb.order.model.OrderDetail;
import com.liaoyb.order.model.OrderMaster;
import com.liaoyb.order.service.OrderService;
import com.liaoyb.product.client.ProductClient;
import com.liaoyb.product.common.DecreaseStockInput;
import com.liaoyb.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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
import java.util.stream.Collectors;

/**
 * 订单
 *
 * @author liaoyb
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    @Transactional
    public Result<OrderResultDTO> createOrder(@Valid @RequestBody OrderForm orderForm) {
        List<String> productIdList = orderForm.getItems().stream().map(OrderForm.OrderProductItem::getProductId).collect(Collectors.toList());
        //通过订单的表单查询商品信息
        com.liaoyb.product.common.Result<List<ProductInfoOutput>> productsResult = productClient.getProducts(productIdList);

        Map<String, List<ProductInfoOutput>> productsByIdMap = productsResult.getData().stream().collect(Collectors.groupingBy(ProductInfoOutput::getProductId));


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
        List<DecreaseStockInput> decreaseStockInputs = new ArrayList<>();
        for (OrderForm.OrderProductItem orderProductItem : orderForm.getItems()) {
            List<ProductInfoOutput> productInfoOutputs = productsByIdMap.get(orderProductItem.getProductId());
            Assert.notEmpty(productInfoOutputs, "商品:" + orderProductItem.getProductId() + "不存在");
            Assert.isTrue(orderProductItem.getProductQuantity() > 0, "购买商品数量需大于0");
            ProductInfoOutput productInfoOutput = productInfoOutputs.get(0);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(IdGenerator.generate());
            orderDetail.setOrderId(orderMaster.getOrderId());
            orderDetail.setProductId(productInfoOutput.getProductId());
            orderDetail.setProductName(productInfoOutput.getProductName());
            orderDetail.setProductPrice(productInfoOutput.getProductPrice());
            orderDetail.setProductQuantity(orderProductItem.getProductQuantity());
            orderDetail.setProductIcon(productInfoOutput.getProductIcon());
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());

            decreaseStockInputs.add(new DecreaseStockInput(orderDetail.getProductId(), orderDetail.getProductQuantity()));
            orderDetails.add(orderDetail);
            totalAmount = totalAmount.add(
                    orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
            );
        }
        com.liaoyb.product.common.Result decreasesStockResult = productClient.decreasesStock(decreaseStockInputs);
        if (!decreasesStockResult.isSuccess()) {
            //库存扣取失败
            log.error("扣取库存失败,原因" + decreasesStockResult.getMsg());
            return Result.fail(-1, "扣取库存失败");
        }

        orderMaster.setOrderAmount(totalAmount);
        //保存订单商品
        orderService.saveOrderDetails(orderDetails);

        //保存订单
        orderService.saveOrderMaster(orderMaster);
        return Result.success(new OrderResultDTO(orderMaster.getOrderId()));
    }
}
