package com.example.orderservice.config;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    private final OrderRepository orderRepository;

    @Autowired
    public DataLoader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        orderRepository.save(new Order(null, "Alice Johnson", 1L, 1, new BigDecimal("1299.99"), LocalDateTime.now(), "COMPLETED"));
        orderRepository.save(new Order(null, "Bob Smith", 2L, 2, new BigDecimal("59.98"), LocalDateTime.now(), "PENDING"));
    }
}
