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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private UserService userService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CategoryRepository categoryRepository) {
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


        return null;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        return "";
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        return null;
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return List.of();
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDisc, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
