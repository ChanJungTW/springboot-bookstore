package com.johnhsu.springbootbookstore.dao;

import com.johnhsu.springbootbookstore.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
