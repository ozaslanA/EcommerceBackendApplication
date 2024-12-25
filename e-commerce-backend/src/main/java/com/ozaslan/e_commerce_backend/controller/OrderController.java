package com.ozaslan.e_commerce_backend.controller;


import com.ozaslan.e_commerce_backend.exceptions.OrderException;
import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.Address;
import com.ozaslan.e_commerce_backend.model.Order;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.service.abstracts.OrderService;
import com.ozaslan.e_commerce_backend.service.abstracts.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<Order> crateOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserProfileByJwt(jwt);

        Order order = orderService.createOrder(user, shippingAddress);

        System.out.println("order" + order);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserProfileByJwt(jwt);

        List<Order> orders = orderService.usersOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(
            @PathVariable("id") Long orderId, @RequestHeader("Authorization") String jwt) throws UserException, OrderException {

        User user = userService.findUserProfileByJwt(jwt);

        Order order = orderService.findOrderById(orderId);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }
}
