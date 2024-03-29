package com.johnhsu.springbootbookstore.controller;

import com.johnhsu.springbootbookstore.dao.ProductQueryParams;
import com.johnhsu.springbootbookstore.dto.ProductRequest;
import com.johnhsu.springbootbookstore.model.Product;
import com.johnhsu.springbootbookstore.service.ProductService;
import com.johnhsu.springbootbookstore.util.Page;
import constant.ProductCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(required=false) ProductCategory category,
                                                     @RequestParam(required = false) String search,
                                                     @RequestParam(defaultValue="created_date") String orderBy,
                                                     @RequestParam(defaultValue="desc") String sort,
                                                     @RequestParam(defaultValue="2") @Max(1000) @Min(0) Integer limit,
                                                     @RequestParam(defaultValue ="0 ") @Min(0) Integer offset

                                                     )
    {
        ProductQueryParams productQueryParams=new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList=productService.getProducts(productQueryParams);
        Integer total=productService.countProduct(productQueryParams);
        Page<Product> page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product=productService.getProductById(productId);
        if(product!=null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId=productService.createProduct(productRequest);
        Product product=productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody @Valid ProductRequest productRequest){
        if(productService.getProductById(productId)!=null){
        productService.updateProduct(productId, productRequest);
        Product updateProduct=productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);}
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
