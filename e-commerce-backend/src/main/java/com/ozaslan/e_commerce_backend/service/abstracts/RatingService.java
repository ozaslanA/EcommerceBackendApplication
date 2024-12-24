package com.ozaslan.e_commerce_backend.service.abstracts;

import com.ozaslan.e_commerce_backend.dtos.request.RatingRequest;
import com.ozaslan.e_commerce_backend.dtos.request.ReviewRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.exceptions.RatingException;
import com.ozaslan.e_commerce_backend.exceptions.ReviewException;
import com.ozaslan.e_commerce_backend.model.Product;
import com.ozaslan.e_commerce_backend.model.Rating;
import com.ozaslan.e_commerce_backend.model.Review;
import com.ozaslan.e_commerce_backend.model.User;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);

    public Rating updateRating(Long ratingId, RatingRequest req, User user) throws RatingException;

}
