package com.johnhsu.springbootbookstore.service;

import com.johnhsu.springbootbookstore.dto.ProductRequest;
import com.johnhsu.springbootbookstore.model.Product;
import org.springframework.stereotype.Component;


public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
