package com.ozaslan.e_commerce_backend.service.abstracts;

import com.ozaslan.e_commerce_backend.exceptions.CartItemException;
import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.Cart;
import com.ozaslan.e_commerce_backend.model.CartItem;
import com.ozaslan.e_commerce_backend.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem) ;

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product,String size,Long userId);

    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
