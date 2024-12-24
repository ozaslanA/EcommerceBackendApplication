package com.ozaslan.e_commerce_backend.service.abstracts;

import com.ozaslan.e_commerce_backend.dtos.request.ReviewRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.exceptions.ReviewException;
import com.ozaslan.e_commerce_backend.model.Review;
import com.ozaslan.e_commerce_backend.model.User;

import java.util.List;

public interface ReviewService {

    public Review createReive(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);

    Review updateReview(Long reviewId, ReviewRequest req, User user) throws ReviewException;

}
