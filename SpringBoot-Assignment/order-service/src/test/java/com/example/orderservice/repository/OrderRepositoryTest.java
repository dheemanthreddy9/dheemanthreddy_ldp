package com.example.orderservice.repository;

import com.example.orderservice.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveAndFindById() {
        Order order = new Order(null, "Charlie Brown", 5L, 1, new BigDecimal("49.99"), LocalDateTime.now(), "PROCESSING");
        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder.getId());

        Optional<Order> found = orderRepository.findById(savedOrder.getId());
        assertTrue(found.isPresent());
        assertEquals("Charlie Brown", found.get().getCustomerName());
    }

    @Test
    void testFindByCustomerName() {
        orderRepository.save(new Order(null, "David Miller", 1L, 2, new BigDecimal("100.00"), LocalDateTime.now(), "PLACED"));
        orderRepository.save(new Order(null, "David Miller", 2L, 1, new BigDecimal("50.00"), LocalDateTime.now(), "DELIVERED"));
        orderRepository.save(new Order(null, "Eva Green", 3L, 4, new BigDecimal("200.00"), LocalDateTime.now(), "PLACED"));

        List<Order> davidOrders = orderRepository.findByCustomerName("David Miller");

        assertEquals(2, davidOrders.size());
    }
}
