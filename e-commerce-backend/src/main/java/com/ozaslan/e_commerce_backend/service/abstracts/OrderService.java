package com.ozaslan.e_commerce_backend.service.abstracts;
import com.ozaslan.e_commerce_backend.exceptions.OrderException;
import com.ozaslan.e_commerce_backend.model.Address;
import com.ozaslan.e_commerce_backend.model.Order;
import com.ozaslan.e_commerce_backend.model.User;
import java.util.List;

public interface OrderService {


    Order createOrder(User user, Address shippingAdress);

    Order findOrderById(Long orderId) throws OrderException;

    List<Order> usersOrderHistory(Long userId);

    Order placedOrder(Long orderId) throws OrderException;

    Order confirmedOrder(Long orderId) throws OrderException;

    Order shippedOrder(Long orderId) throws OrderException;

    Order deliveredOrder(Long orderId) throws OrderException;

    Order canceledOrder(Long orderId) throws OrderException;

    List<Order> getAllOrders();

    Order deleteOrder(Long orderId) throws OrderException;


}
