package com.yuyang.sprignbootmall.service;

import com.yuyang.sprignbootmall.dto.CreateOrderRequest;
import com.yuyang.sprignbootmall.dto.OrderQueryParam;
import com.yuyang.sprignbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    Integer countOrder(OrderQueryParam orderQueryParam);

    List<Order> getOrders(OrderQueryParam orderQueryParam);
}
