package com.yuyang.sprignbootmall.dao.impl;

import com.yuyang.sprignbootmall.dao.OrderDao;
import com.yuyang.sprignbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = " INSERT INTO  `order`(user_id, total_amount, created_date, last_modified_date) " +
                " VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate) ";

        Map<String ,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        //使用for loop的方式一次一次加入
//        for(OrderItem orderItem : orderItemList){
//            String sql = " INSERT INTO order_item( order_id, product_id, quantity, amount) " +
//                    " VALUES (:orderId, :productId, :quantity, :amount) ";
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("orderId", orderId);
//            map.put("productId", orderItem.getProductId());
//            map.put("quantity", orderItem.getQuantity());
//            map.put("amount", orderItem.getAmount());
//
//            namedParameterJdbcTemplate.update(sql, map);
//        }

        //使用batchUpdate 方法 一次性插入數據效率較高
        MapSqlParameterSource[] mapSqlParameterSources = new MapSqlParameterSource[orderItemList.size()];
        String sql = " INSERT INTO order_item( order_id, product_id, quantity, amount) " +
                " VALUES (:orderId, :productId, :quantity, :amount) ";
        for (int i = 0; i<orderItemList.size(); i++){
            OrderItem orderItem = orderItemList.get(i);

            mapSqlParameterSources[i]= new MapSqlParameterSource();
            mapSqlParameterSources[i].addValue("orderId", orderId);
            mapSqlParameterSources[i].addValue("productId", orderItem.getProductId());
            mapSqlParameterSources[i].addValue("quantity", orderItem.getQuantity());
            mapSqlParameterSources[i].addValue("amount", orderItem.getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSources);
    }
}
