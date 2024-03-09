package com.johnhsu.springbootbookstore.service.impl;

import com.johnhsu.springbootbookstore.dao.*;
import com.johnhsu.springbootbookstore.dao.ProductDao;
import com.johnhsu.springbootbookstore.dto.BuyItem;
import com.johnhsu.springbootbookstore.dto.CreateOrderRequest;
import com.johnhsu.springbootbookstore.model.Order;
import com.johnhsu.springbootbookstore.model.OrderItem;
import com.johnhsu.springbootbookstore.model.Product;
import com.johnhsu.springbootbookstore.model.User;
import com.johnhsu.springbootbookstore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    private final static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order=orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList=orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user=userDao.getUserById(userId);
        if(user==null){
            log.warn("userId {} isn't existed!",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int totalAmount=0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem:createOrderRequest.getBuyItemList()){
            Product product=productDao.getProductById(buyItem.getProductId());

            if(product==null){
                log.warn("The product isn't exised!",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock()<buyItem.getQuantity()){
                log.warn("The product{}'s stock isn't enough, current stock is {}, buying quantity is {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            productDao.updateStock(product.getProductId(),product.getStock()-buyItem.getQuantity());
            

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
