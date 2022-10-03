package com.yuyang.sprignbootmall.dao;

import com.yuyang.sprignbootmall.dto.UserRegisterRequest;
import com.yuyang.sprignbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
