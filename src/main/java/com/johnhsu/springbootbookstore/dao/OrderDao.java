package com.johnhsu.springbootbookstore.dao;

import com.johnhsu.springbootbookstore.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
