package com.johnhsu.springbootbookstore.service.impl;

import com.johnhsu.springbootbookstore.dao.UserDao;
import com.johnhsu.springbootbookstore.dto.UserRegisterRequest;
import com.johnhsu.springbootbookstore.model.User;
import com.johnhsu.springbootbookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
