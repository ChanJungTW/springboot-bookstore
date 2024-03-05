package com.johnhsu.springbootbookstore.dao;

import com.johnhsu.springbootbookstore.dto.UserRegisterRequest;
import com.johnhsu.springbootbookstore.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
