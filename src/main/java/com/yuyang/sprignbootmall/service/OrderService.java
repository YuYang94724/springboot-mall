package com.yuyang.sprignbootmall.service;

import com.yuyang.sprignbootmall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
