package com.johnhsu.springbootbookstore.controller;

import com.johnhsu.springbootbookstore.dto.UserLoginRequest;
import com.johnhsu.springbootbookstore.dto.UserRegisterRequest;
import com.johnhsu.springbootbookstore.model.User;
import com.johnhsu.springbootbookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
            Integer userId=userService.register(userRegisterRequest);
            User user=userService.getUserById(userId);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user=userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
