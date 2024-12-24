package com.ozaslan.e_commerce_backend.service.concrates;

import com.ozaslan.e_commerce_backend.dtos.request.ReviewRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.exceptions.ReviewException;
import com.ozaslan.e_commerce_backend.model.Product;
import com.ozaslan.e_commerce_backend.model.Rating;
import com.ozaslan.e_commerce_backend.model.Review;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.repository.ReviewRapository;
import com.ozaslan.e_commerce_backend.service.abstracts.ProductService;
import com.ozaslan.e_commerce_backend.service.abstracts.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    public final ReviewRapository reviewRapository;
    public final ProductService productService;


    public ReviewServiceImpl(ReviewRapository reviewRapository, ProductService productService) {
        this.reviewRapository = reviewRapository;
        this.productService = productService;
    }

    @Override
    public Review createReive(ReviewRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRapository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRapository.getAllProductsReview(productId);
    }

    @Override
    public Review updateReview(Long reviewId, ReviewRequest req, User user) throws ReviewException {

        Optional<Review> optionalReview = reviewRapository.findById(reviewId);

        if (optionalReview.isEmpty()) {
            throw new ReviewException("Review not foundt for ID: " + reviewId);
        }

        Review review = optionalReview.get();
        if (!review.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("User is not authorized to update this review");
        }

        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRapository.save(review);
    }
}
