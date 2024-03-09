package com.johnhsu.springbootbookstore.service;

import com.johnhsu.springbootbookstore.dto.CreateOrderRequest;
import com.johnhsu.springbootbookstore.dto.OrderQueryParams;
import com.johnhsu.springbootbookstore.model.Order;

import java.util.List;

public interface OrderService {
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
