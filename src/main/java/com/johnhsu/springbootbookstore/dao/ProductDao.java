package com.johnhsu.springbootbookstore.dao;

import com.johnhsu.springbootbookstore.dto.ProductRequest;
import com.johnhsu.springbootbookstore.model.Product;
import constant.ProductCategory;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
