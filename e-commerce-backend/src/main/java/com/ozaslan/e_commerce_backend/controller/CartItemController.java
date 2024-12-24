package com.ozaslan.e_commerce_backend.controller;


import com.ozaslan.e_commerce_backend.exceptions.CartItemException;
import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.Cart;
import com.ozaslan.e_commerce_backend.model.CartItem;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.service.abstracts.CartItemService;
import com.ozaslan.e_commerce_backend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {


    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem, @RequestHeader("Authorization") String jwt) throws UserException {


        User user = userService.findUserProfileByJwt(jwt);
        cartItem.setUserId(user.getId());
        CartItem createCartItem = cartItemService.createCartItem(cartItem);
        return new ResponseEntity<>(createCartItem, HttpStatus.CREATED);


    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable("id") Long id,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String jwt) throws CartItemException, UserException {

        User user = userService.findUserProfileByJwt(jwt);
        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), id, cartItem);

        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}/remove")
    public ResponseEntity<String> removeCartItem(
            @PathVariable("cartItemId") Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws CartItemException, UserException {

        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);
        return new ResponseEntity<>("Cart item removed successfully", HttpStatus.OK);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> findCartItemById(@PathVariable("cartItemId") Long cartItemId) throws CartItemException {
        CartItem cartItem = cartItemService.findCartItemById(cartItemId);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }


}
