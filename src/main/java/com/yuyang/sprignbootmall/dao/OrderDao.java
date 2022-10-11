package com.yuyang.sprignbootmall.dao;

import com.yuyang.sprignbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void  createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
