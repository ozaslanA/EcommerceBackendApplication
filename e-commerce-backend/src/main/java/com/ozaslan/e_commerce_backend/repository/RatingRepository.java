package com.ozaslan.e_commerce_backend.repository;

import com.ozaslan.e_commerce_backend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
    List<Rating> getAllProductsRating(@Param("productId") Long productId);

}
