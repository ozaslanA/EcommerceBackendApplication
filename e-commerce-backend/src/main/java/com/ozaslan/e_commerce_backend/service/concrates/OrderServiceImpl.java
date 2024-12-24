package com.ozaslan.e_commerce_backend.service.concrates;

import com.ozaslan.e_commerce_backend.exceptions.OrderException;
import com.ozaslan.e_commerce_backend.model.Order;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.repository.CartRepository;
import com.ozaslan.e_commerce_backend.service.abstracts.CartService;
import com.ozaslan.e_commerce_backend.service.abstracts.OrderService;
import com.ozaslan.e_commerce_backend.service.abstracts.ProductService;
import jakarta.mail.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final CartService cartService;
    private final ProductService productService;


    public OrderServiceImpl(CartRepository cartRepository, CartService cartService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public Order createOrder(User user, Address shippingAdress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }
}
