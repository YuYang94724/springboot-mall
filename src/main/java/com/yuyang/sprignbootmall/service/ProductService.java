package com.yuyang.sprignbootmall.service;

import com.yuyang.sprignbootmall.dto.ProductRequest;
import com.yuyang.sprignbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void  updatedProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
