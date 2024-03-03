package com.johnhsu.springbootbookstore.dao.impl;

import com.johnhsu.springbootbookstore.dao.ProductDao;
import com.johnhsu.springbootbookstore.model.Product;
import com.johnhsu.springbootbookstore.rowmapper.ProductRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductById(Integer productId) {
        String sql="SELECT * FROM product WHERE product_id = :productId";
        Map<String, Object> map=new HashMap<>();
        map.put("productId",productId);

        List<Product> productList=namedParameterJdbcTemplate.query(sql,map, new ProductRowmapper());
        if(productList.size()>0){
            return productList.get(0);
        }else{
            return null;
        }
    }
}
