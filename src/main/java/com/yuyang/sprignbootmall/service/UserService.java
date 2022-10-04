package com.yuyang.sprignbootmall.service;

import com.yuyang.sprignbootmall.dao.UserDao;
import com.yuyang.sprignbootmall.dto.UserLoginRequest;
import com.yuyang.sprignbootmall.dto.UserRegisterRequest;
import com.yuyang.sprignbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}
