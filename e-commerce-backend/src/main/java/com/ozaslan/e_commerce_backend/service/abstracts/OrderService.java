package com.ozaslan.e_commerce_backend.service.abstracts;

import com.ozaslan.e_commerce_backend.exceptions.OrderException;
import com.ozaslan.e_commerce_backend.model.Order;
import com.ozaslan.e_commerce_backend.model.User;
import jakarta.mail.Address;
import org.aspectj.weaver.ast.Or;

import java.util.List;


public interface OrderService {


    public Order createOrder(User user, Address shippingAdress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

}
