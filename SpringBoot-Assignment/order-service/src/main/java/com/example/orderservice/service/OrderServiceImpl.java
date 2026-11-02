package com.example.orderservice.service;

import com.example.orderservice.client.ProductServiceClient;
import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.exception.ProductServiceException;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductServiceClient productServiceClient) {
        this.orderRepository = orderRepository;
        this.productServiceClient = productServiceClient;
    }

    @Override
    public Order createOrder(Order order) {
        // Validate Product existence via Inter-Service client to Product Microservice
        ProductDto product = productServiceClient.getProductById(order.getProductId());
        
        if (product.getStockQuantity() < order.getQuantity()) {
            throw new ProductServiceException("Insufficient stock for product ID: " + order.getProductId() +
                    ". Available: " + product.getStockQuantity() + ", Requested: " + order.getQuantity());
        }

        // Calculate total price based on Product Microservice unit price
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PLACED");
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        Order existingOrder = getOrderById(id);
        
        if (orderDetails.getQuantity() != null && !orderDetails.getQuantity().equals(existingOrder.getQuantity())) {
            ProductDto product = productServiceClient.getProductById(existingOrder.getProductId());
            existingOrder.setQuantity(orderDetails.getQuantity());
            existingOrder.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity())));
        }

        if (orderDetails.getStatus() != null) {
            existingOrder.setStatus(orderDetails.getStatus());
        }

        if (orderDetails.getCustomerName() != null) {
            existingOrder.setCustomerName(orderDetails.getCustomerName());
        }

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order existingOrder = getOrderById(id);
        orderRepository.delete(existingOrder);
    }
}
