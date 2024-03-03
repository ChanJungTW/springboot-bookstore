package com.johnhsu.springbootbookstore.dao;

import com.johnhsu.springbootbookstore.dto.ProductRequest;
import com.johnhsu.springbootbookstore.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
