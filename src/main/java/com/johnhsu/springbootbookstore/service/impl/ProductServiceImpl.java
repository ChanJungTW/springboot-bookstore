package com.johnhsu.springbootbookstore.service.impl;

import com.johnhsu.springbootbookstore.dao.ProductDao;
import com.johnhsu.springbootbookstore.model.Product;
import com.johnhsu.springbootbookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }
}
