package org.example.repository;

import org.example.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public ProductRepository() {
        save(new Product(null, "Laptop", "Electronics", new BigDecimal("999.99"), true));
        save(new Product(null, "Wireless Mouse", "Electronics", new BigDecimal("29.99"), true));
        save(new Product(null, "Office Chair", "Furniture", new BigDecimal("199.99"), true));
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean existsByName(String name) {
        return store.values().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(name));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(idSequence.incrementAndGet());
        }
        store.put(product.getId(), product);
        return product;
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
