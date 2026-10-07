package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFindById() {
        Product product = new Product(null, "Headphones", "Noise canceling headphones", new BigDecimal("199.99"), 15);
        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());

        Optional<Product> found = productRepository.findById(savedProduct.getId());
        assertTrue(found.isPresent());
        assertEquals("Headphones", found.get().getName());
    }

    @Test
    void testFindByNameContainingIgnoreCase() {
        productRepository.save(new Product(null, "Apple iPhone 15", "Smartphone", new BigDecimal("999.99"), 10));
        productRepository.save(new Product(null, "Apple iPad Air", "Tablet", new BigDecimal("599.99"), 12));
        productRepository.save(new Product(null, "Dell XPS 13", "Laptop", new BigDecimal("1199.99"), 5));

        List<Product> appleProducts = productRepository.findByNameContainingIgnoreCase("apple");

        assertEquals(2, appleProducts.size());
    }
}
