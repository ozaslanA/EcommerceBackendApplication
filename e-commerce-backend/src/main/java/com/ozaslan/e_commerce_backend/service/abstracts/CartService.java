package com.ozaslan.e_commerce_backend.service.abstracts;

import com.ozaslan.e_commerce_backend.dtos.request.AddItemRequest;
import com.ozaslan.e_commerce_backend.exceptions.ProductException;
import com.ozaslan.e_commerce_backend.model.Cart;
import com.ozaslan.e_commerce_backend.model.User;

public interface CartService {

    public Cart createCart(User user);

    public  String addCartItem(Long userId, AddItemRequest req)throws ProductException;

    public Cart findUserCart(Long userId);
}
