package com.ozaslan.e_commerce_backend.service.abstracts;

import com.ozaslan.e_commerce_backend.dtos.request.CreateProductRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.model.Product;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {

    public Product createProduct(CreateProductRequest req);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId,Product req) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category);

    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDisc, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);}
