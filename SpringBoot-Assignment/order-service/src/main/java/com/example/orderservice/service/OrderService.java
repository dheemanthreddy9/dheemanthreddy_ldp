package com.example.orderservice.service;

import com.example.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order updateOrder(Long id, Order orderDetails);
    void deleteOrder(Long id);
}
