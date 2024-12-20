package com.ozaslan.e_commerce_backend.repository;

import com.ozaslan.e_commerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR p.category = :category) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:minDiscount IS NULL OR p.discount >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'priceAsc' THEN p.price END ASC, " +
            "CASE WHEN :sort = 'priceDesc' THEN p.price END DESC, " +
            "CASE WHEN :sort = 'discountAsc' THEN p.discount END ASC, " +
            "CASE WHEN :sort = 'discountDesc' THEN p.discount END DESC"
    )
    List<Product> filterProducts(
            @Param("category") String category,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minDiscount") Integer minDiscount,
            @Param("sort") String sort
    );
}
