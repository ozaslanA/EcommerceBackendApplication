package com.ozaslan.e_commerce_backend.controller;


import com.ozaslan.e_commerce_backend.model.Product;
import com.ozaslan.e_commerce_backend.service.abstracts.ProductService;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>>findProductByCategoryHandler
}
