package com.johnhsu.springbootbookstore.controller;

import com.johnhsu.springbootbookstore.dto.CreateOrderRequest;
import com.johnhsu.springbootbookstore.model.Order;
import com.johnhsu.springbootbookstore.model.OrderItem;
import com.johnhsu.springbootbookstore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                               @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId=orderService.createOrder(userId, createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

}
