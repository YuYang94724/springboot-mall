package com.yuyang.sprignbootmall.service.impl;

import com.yuyang.sprignbootmall.dao.ProductDao;
import com.yuyang.sprignbootmall.model.Product;
import com.yuyang.sprignbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
