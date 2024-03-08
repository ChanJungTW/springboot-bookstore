package com.johnhsu.springbootbookstore.service.impl;

import com.johnhsu.springbootbookstore.dao.OrderDao;
import com.johnhsu.springbootbookstore.dao.ProductDao;
import com.johnhsu.springbootbookstore.dto.BuyItem;
import com.johnhsu.springbootbookstore.dto.CreateOrderRequest;
import com.johnhsu.springbootbookstore.model.OrderItem;
import com.johnhsu.springbootbookstore.model.Product;
import com.johnhsu.springbootbookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount=0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem:createOrderRequest.getBuyItemList()){
            Product product=productDao.getProductById(buyItem.getProductId());

            int amount=buyItem.getQuantity()*product.getPrice();
            totalAmount=totalAmount+amount;
            OrderItem orderItem=new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(totalAmount);
            orderItemList.add(orderItem);
        }
        Integer orderId=orderDao.createOrder(userId,totalAmount);
        orderDao.createOrderItems(orderId,orderItemList);

        return orderId;
    }
}
