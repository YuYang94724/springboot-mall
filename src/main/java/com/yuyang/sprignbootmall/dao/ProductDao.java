package com.yuyang.sprignbootmall.dao;

import com.yuyang.sprignbootmall.dto.ProductRequest;
import com.yuyang.sprignbootmall.model.Product;

public interface ProductDao {

    public Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
