package com.ozaslan.e_commerce_backend.service.concrates;

import com.ozaslan.e_commerce_backend.exceptions.CartItemException;
import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.Cart;
import com.ozaslan.e_commerce_backend.model.CartItem;
import com.ozaslan.e_commerce_backend.model.Product;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.repository.CartItemRepository;
import com.ozaslan.e_commerce_backend.repository.CartRepository;
import com.ozaslan.e_commerce_backend.service.abstracts.CartItemService;
import com.ozaslan.e_commerce_backend.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public CartItemServiceImpl(UserService userService, CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if (user.getId().equals(user)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());

        }
        return cartItemRepository.save(item);

    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExist(cart, product, size, userId).orElse(null);
    }


    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(userId);
        User reqUser = userService.findUserById(userId);

        if (user.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new UserException("You can't remove another users item");
        }

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);

        if (optionalCartItem.isPresent()) {
            if (optionalCartItem.isPresent()) {
                return optionalCartItem.get();
            }
            throw new CartItemException("CartItem not found with id:" + cartItemId);
        }
        return null;
    }
}
