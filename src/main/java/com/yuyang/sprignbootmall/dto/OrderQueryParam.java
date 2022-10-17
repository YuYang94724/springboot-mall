package com.yuyang.sprignbootmall.dto;

import lombok.Data;

@Data
public class OrderQueryParam {
    private Integer userId;
    private Integer limit;
    private Integer offset;
}
