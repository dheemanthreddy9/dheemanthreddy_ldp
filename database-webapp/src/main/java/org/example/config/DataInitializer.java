package org.example.config;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository repository;

    public DataInitializer(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.save(new Product("Laptop", 999.99, "Electronics"));
        repository.save(new Product("Wireless Mouse", 29.99, "Accessories"));
        repository.save(new Product("Mechanical Keyboard", 89.99, "Accessories"));
    }
}
