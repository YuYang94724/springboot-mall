package com.yuyang.sprignbootmall.dao;

import com.yuyang.sprignbootmall.dto.OrderQueryParam;
import com.yuyang.sprignbootmall.model.Order;
import com.yuyang.sprignbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void  createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsById(Integer orderId);

    List<Order> getOrders(OrderQueryParam orderQueryParam);

    Integer countOrder(OrderQueryParam orderQueryParam);
}
