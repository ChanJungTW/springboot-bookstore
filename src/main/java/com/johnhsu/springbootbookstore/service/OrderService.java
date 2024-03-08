package com.johnhsu.springbootbookstore.service;

import com.johnhsu.springbootbookstore.dto.CreateOrderRequest;
import com.johnhsu.springbootbookstore.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
