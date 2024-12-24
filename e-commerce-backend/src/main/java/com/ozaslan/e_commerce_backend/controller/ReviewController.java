package com.ozaslan.e_commerce_backend.controller;


import com.ozaslan.e_commerce_backend.dtos.request.RatingRequest;
import com.ozaslan.e_commerce_backend.dtos.request.ReviewRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.exceptions.RatingException;
import com.ozaslan.e_commerce_backend.exceptions.ReviewException;
import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.Rating;
import com.ozaslan.e_commerce_backend.model.Review;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.service.abstracts.RatingService;
import com.ozaslan.e_commerce_backend.service.abstracts.ReviewService;
import com.ozaslan.e_commerce_backend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReive(req, user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Review> reviews = reviewService.getAllReview(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}/update")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest req,
            @RequestHeader("Authorization") String jwt) throws UserException, ReviewException {
        User user = userService.findUserProfileByJwt(jwt);
        Review updatedReview = reviewService.updateReview(reviewId, req, user);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }
}