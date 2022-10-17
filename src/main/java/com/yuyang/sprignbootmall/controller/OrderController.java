package com.yuyang.sprignbootmall.controller;

import com.yuyang.sprignbootmall.dto.CreateOrderRequest;
import com.yuyang.sprignbootmall.dto.OrderQueryParam;
import com.yuyang.sprignbootmall.model.Order;
import com.yuyang.sprignbootmall.service.OrderService;
import com.yuyang.sprignbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.CacheRequest;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/users/{userId}/orders")
    public  ResponseEntity<?> getOrders(@PathVariable Integer userId,
                                        @RequestParam(defaultValue = "10") @Max(100) @Min(0) Integer limit,
                                        @RequestParam(defaultValue = "0") @Min(0) Integer offset){

        OrderQueryParam orderQueryParam = new OrderQueryParam();
        orderQueryParam.setUserId(userId);
        orderQueryParam.setLimit(limit);
        orderQueryParam.setOffset(offset);

        //取得order list
        List<Order> orderList = orderService.getOrders(orderQueryParam);
        //取得order 總數
        Integer count = orderService.countOrder(orderQueryParam);

        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
