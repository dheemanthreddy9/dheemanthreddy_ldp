package com.example.orderservice.service;

import com.example.orderservice.client.ProductServiceClient;
import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.exception.ProductServiceException;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductServiceClient productServiceClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order sampleOrder;
    private ProductDto sampleProductDto;

    @BeforeEach
    void setUp() {
        sampleOrder = new Order(1L, "John Doe", 10L, 2, new BigDecimal("200.00"), LocalDateTime.now(), "PLACED");
        sampleProductDto = new ProductDto(10L, "Test Product", "Description", new BigDecimal("100.00"), 50);
    }

    @Test
    void testCreateOrder_Success() {
        when(productServiceClient.getProductById(10L)).thenReturn(sampleProductDto);
        when(orderRepository.save(any(Order.class))).thenReturn(sampleOrder);

        Order created = orderService.createOrder(sampleOrder);

        assertNotNull(created);
        assertEquals("John Doe", created.getCustomerName());
        assertEquals(new BigDecimal("200.00"), created.getTotalPrice());
        verify(orderRepository, times(1)).save(sampleOrder);
    }

    @Test
    void testCreateOrder_InsufficientStock() {
        ProductDto lowStockProduct = new ProductDto(10L, "Test Product", "Desc", new BigDecimal("100.00"), 1);
        when(productServiceClient.getProductById(10L)).thenReturn(lowStockProduct);

        assertThrows(ProductServiceException.class, () -> orderService.createOrder(sampleOrder));
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(sampleOrder));

        List<Order> orders = orderService.getAllOrders();

        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(sampleOrder));

        Order found = orderService.getOrderById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderById(99L));
    }

    @Test
    void testUpdateOrder() {
        Order updateDetails = new Order();
        updateDetails.setStatus("CANCELLED");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(sampleOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(sampleOrder);

        Order updated = orderService.updateOrder(1L, updateDetails);

        assertNotNull(updated);
        assertEquals("CANCELLED", updated.getStatus());
        verify(orderRepository, times(1)).save(sampleOrder);
    }

    @Test
    void testDeleteOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(sampleOrder));
        doNothing().when(orderRepository).delete(sampleOrder);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).delete(sampleOrder);
    }
}
