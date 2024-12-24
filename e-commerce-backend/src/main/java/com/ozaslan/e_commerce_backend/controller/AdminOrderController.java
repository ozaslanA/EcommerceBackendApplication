package com.ozaslan.e_commerce_backend.controller;


import com.ozaslan.e_commerce_backend.dtos.response.ApiResponse;
import com.ozaslan.e_commerce_backend.exceptions.OrderException;
import com.ozaslan.e_commerce_backend.model.Order;
import com.ozaslan.e_commerce_backend.service.abstracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersHandler() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<ApiResponse> ConfirmedOrderHandler(@PathVariable Long orderId) throws OrderException {
        orderService.confirmedOrder(orderId);
        ApiResponse response = new ApiResponse("Order confirmed successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<ApiResponse> ShippedOrderHandler(@PathVariable Long orderId) throws OrderException {
        orderService.shippedOrder(orderId);
        ApiResponse response = new ApiResponse("Order shipped successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<ApiResponse> DeliverOrderHandler(@PathVariable Long orderId) throws OrderException {
        orderService.deliveredOrder(orderId);
        ApiResponse response = new ApiResponse("Order delivered successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<ApiResponse> CancelOrderHandler(@PathVariable Long orderId) throws OrderException {
        orderService.canceledOrder(orderId);
        ApiResponse response = new ApiResponse("Order canceled successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long orderId) throws OrderException {
        orderService.deleteOrder(orderId);
        ApiResponse response = new ApiResponse("Order deleted successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
