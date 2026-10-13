package com.example.productservice.config;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null, "Laptop Pro", "High performance laptop", new BigDecimal("1299.99"), 15));
        productRepository.save(new Product(null, "Wireless Mouse", "Ergonomic optical mouse", new BigDecimal("29.99"), 50));
        productRepository.save(new Product(null, "Mechanical Keyboard", "RGB mechanical keyboard", new BigDecimal("89.99"), 30));
    }
}
