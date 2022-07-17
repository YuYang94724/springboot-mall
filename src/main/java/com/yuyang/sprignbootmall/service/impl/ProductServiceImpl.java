package com.yuyang.sprignbootmall.service.impl;

import com.yuyang.sprignbootmall.constant.ProductCategory;
import com.yuyang.sprignbootmall.dao.ProductDao;
import com.yuyang.sprignbootmall.dto.ProductRequest;
import com.yuyang.sprignbootmall.model.Product;
import com.yuyang.sprignbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductCategory productCategory, String search) {
        return productDao.getProducts(productCategory, search);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updatedProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
