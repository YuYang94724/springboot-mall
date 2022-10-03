package com.yuyang.sprignbootmall.service.impl;

import com.yuyang.sprignbootmall.dao.UserDao;
import com.yuyang.sprignbootmall.dto.UserRegisterRequest;
import com.yuyang.sprignbootmall.model.User;
import com.yuyang.sprignbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
