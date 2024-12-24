package com.ozaslan.e_commerce_backend.controller;


import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.model.Product;
import com.ozaslan.e_commerce_backend.service.abstracts.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category, @RequestParam List<String> color, @RequestParam List<String> size, @RequestParam Integer minPrice, @RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort, @RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        Page<Product> res = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);

        System.out.println("complete products");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product>findProductByIdHandler(@PathVariable Long productId)throws ProductException{
        Product product=productService.findProductById(productId);
        return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
    }   }


