package com.johnhsu.springbootbookstore.service;

import com.johnhsu.springbootbookstore.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
