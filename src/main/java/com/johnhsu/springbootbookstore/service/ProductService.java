package com.johnhsu.springbootbookstore.service;

import com.johnhsu.springbootbookstore.dao.ProductQueryParams;
import com.johnhsu.springbootbookstore.dto.ProductRequest;
import com.johnhsu.springbootbookstore.model.Product;

import java.util.List;


public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
