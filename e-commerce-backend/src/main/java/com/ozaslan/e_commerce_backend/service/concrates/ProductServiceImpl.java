package com.ozaslan.e_commerce_backend.service.concrates;

import com.ozaslan.e_commerce_backend.dtos.request.CreateProductRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.model.Category;
import com.ozaslan.e_commerce_backend.model.Product;
import com.ozaslan.e_commerce_backend.repository.CategoryRepository;
import com.ozaslan.e_commerce_backend.repository.ProductRepository;
import com.ozaslan.e_commerce_backend.service.abstracts.ProductService;
import com.ozaslan.e_commerce_backend.service.abstracts.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(UserService userService, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());

        if (topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParant(req.getSecondLevelCategory(), topLevel.getName());
        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(req.getTopLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);

        }

        Category thirdLevel = categoryRepository.findByNameAndParant(req.getThirdLevelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(req.getTopLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLevelCategory);

        }

        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountedPersent(req.getDiscountedPrice());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(topLevel);
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);


    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        if (product == null) {
            throw new ProductException("Product not found");
        }
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted Successfully";
    }

    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product = findProductById(productId);

        if (product == null) {
            throw new ProductException("Product not found");
        }

        if (req.getTitle() != null) {
            product.setTitle(req.getTitle());
        }
        if (req.getColor() != null) {
            product.setColor(req.getColor());
        }
        if (req.getDescription() != null) {
            product.setDescription(req.getDescription());
        }
        if (req.getDiscountedPrice() != 0) {
            product.setDiscountedPrice(req.getDiscountedPrice());
        }
        if (req.getDiscountedPersent() != 0) {
            product.setDiscountedPersent(req.getDiscountedPersent());
        }
        if (req.getImageUrl() != null) {
            product.setImageUrl(req.getImageUrl());
        }
        if (req.getBrand() != null) {
            product.setBrand(req.getBrand());
        }
        if (req.getPrice() != 0) {
            product.setPrice(req.getPrice());
        }
        if (req.getSizes() != null && !req.getSizes().isEmpty()) {
            product.setSizes(req.getSizes());
        }
        if (req.getQuantity() != 0) {
            product.setQuantity(req.getQuantity());
        }
        if (req.getCategory() != null) {
            product.setCategory(req.getCategory());
        }

        Product updatedProduct = productRepository.save(product);

        return updatedProduct;
    }


    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new ProductException("Product not found with id - " + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return List.of();
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDisc, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
        if (!colors.isEmpty()) {
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }

        if (stock != null) {
            products = products.stream()
                    .filter(p -> stock.equals("in_stock") ? p.getQuantity() > 0 : p.getQuantity() < 1)
                    .collect(Collectors.toList());
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
        return filteredProducts;

    }
}
