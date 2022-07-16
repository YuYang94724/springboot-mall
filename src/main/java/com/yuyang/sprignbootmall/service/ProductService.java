package com.yuyang.sprignbootmall.service;

import com.yuyang.sprignbootmall.dto.ProductRequest;
import com.yuyang.sprignbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void  updatedProduct(Integer productId, ProductRequest productRequest);
}
