package com.ozaslan.e_commerce_backend.controller;


import com.ozaslan.e_commerce_backend.dtos.request.RatingRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.exceptions.RatingException;
import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.Rating;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.service.abstracts.RatingService;
import com.ozaslan.e_commerce_backend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {

        User user = userService.findUserProfileByJwt(jwt);

        Rating rating = ratingService.createRating(req, user);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId, @RequestHeader("Auhorization") String jwt) throws UserException, ProductException {

        User user = userService.findUserProfileByJwt(jwt);

        List<Rating> ratings = ratingService.getProductsRating(productId);

        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }


    @PutMapping("/{ratingId}/update")
    public ResponseEntity<Rating> updateRating(
            @PathVariable Long ratingId,
            @RequestBody RatingRequest req,
            @RequestHeader("Authorization") String jwt) throws UserException, RatingException {

        User user = userService.findUserProfileByJwt(jwt);
        Rating updatedRating = ratingService.updateRating(ratingId, req, user);
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }
}