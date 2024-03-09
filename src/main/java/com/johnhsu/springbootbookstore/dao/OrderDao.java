package com.johnhsu.springbootbookstore.dao;

import com.johnhsu.springbootbookstore.dto.OrderQueryParams;
import com.johnhsu.springbootbookstore.model.Order;
import com.johnhsu.springbootbookstore.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParam);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
