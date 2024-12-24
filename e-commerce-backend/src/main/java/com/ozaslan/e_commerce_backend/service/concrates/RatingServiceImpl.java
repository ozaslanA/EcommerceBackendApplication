package com.ozaslan.e_commerce_backend.service.concrates;
import com.ozaslan.e_commerce_backend.dtos.request.RatingRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.exceptions.RatingException;
import com.ozaslan.e_commerce_backend.model.Product;
import com.ozaslan.e_commerce_backend.model.Rating;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.repository.RatingRepository;
import com.ozaslan.e_commerce_backend.service.abstracts.ProductService;
import com.ozaslan.e_commerce_backend.service.abstracts.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final ProductService productService;

    public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }


    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }

    @Override
    public Rating updateRating(Long ratingId, RatingRequest req, User user) throws RatingException {
        Optional<Rating> optionalRating = ratingRepository.findById(ratingId);


        if (optionalRating.isEmpty()) {
            throw new RatingException("Rating not found for ID: " + ratingId);
        }
        Rating rating = optionalRating.get();
        if (!rating.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("User is not authorized to update this rating");
        }

        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }
}
