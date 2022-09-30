package com.yuyang.sprignbootmall.dao;

import com.yuyang.sprignbootmall.constant.ProductCategory;
import com.yuyang.sprignbootmall.dto.ProductQueryParams;
import com.yuyang.sprignbootmall.dto.ProductRequest;
import com.yuyang.sprignbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    public Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
