package com.yuyang.sprignbootmall.dto;

import com.yuyang.sprignbootmall.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductQueryParams {

   private ProductCategory productCategory;
   private String search;
   private String orderBy;
   private String sort;
}
