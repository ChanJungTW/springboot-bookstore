package com.johnhsu.springbootbookstore.service;

import com.johnhsu.springbootbookstore.dto.UserRegisterRequest;
import com.johnhsu.springbootbookstore.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);


}
