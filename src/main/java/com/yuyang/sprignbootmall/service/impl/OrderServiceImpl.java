package com.yuyang.sprignbootmall.service.impl;

import com.yuyang.sprignbootmall.dao.OrderDao;
import com.yuyang.sprignbootmall.dao.ProductDao;
import com.yuyang.sprignbootmall.dao.UserDao;
import com.yuyang.sprignbootmall.dto.BuyItem;
import com.yuyang.sprignbootmall.dto.CreateOrderRequest;
import com.yuyang.sprignbootmall.model.Order;
import com.yuyang.sprignbootmall.model.OrderItem;
import com.yuyang.sprignbootmall.model.Product;
import com.yuyang.sprignbootmall.model.User;
import com.yuyang.sprignbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //檢查該用戶是否存在
        User user = userDao.getUserById(userId);
        if (user == null){
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查商品是否存在, 商品庫存是否足夠
            if(product == null){
                log.warn("該商品 {} 不存在",product.getProductName());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買，剩餘庫存 {}，欲購買數量 {}",
                        product.getProductName(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //都沒問題，購買時順便購儲商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            //計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;
            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }


        //創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        var orderItemList = orderDao.getOrderItemsById(orderId);

        order.setOrderItemList(orderItemList);
        return order;
    }
}
