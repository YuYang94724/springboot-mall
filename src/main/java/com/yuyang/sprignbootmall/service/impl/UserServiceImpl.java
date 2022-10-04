package com.yuyang.sprignbootmall.service.impl;

import com.yuyang.sprignbootmall.dao.UserDao;
import com.yuyang.sprignbootmall.dto.UserRegisterRequest;
import com.yuyang.sprignbootmall.model.User;
import com.yuyang.sprignbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查email是否已註冊過
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());

        if( user != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //註冊新email
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
