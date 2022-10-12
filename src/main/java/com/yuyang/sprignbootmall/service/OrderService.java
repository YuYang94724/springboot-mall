package com.yuyang.sprignbootmall.service;

import com.yuyang.sprignbootmall.dto.CreateOrderRequest;
import com.yuyang.sprignbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
