package org.example.service;

import org.example.dto.ProductRequestDto;
import org.example.dto.ProductResponseDto;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> getAllProducts(String category, String search) {
        return productRepository.findAll().stream()
                .filter(p -> category == null || category.trim().isEmpty() || p.getCategory().equalsIgnoreCase(category.trim()))
                .filter(p -> search == null || search.trim().isEmpty() || p.getName().toLowerCase().contains(search.toLowerCase().trim()))
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDto> getProductById(Long id) {
        return productRepository.findById(id).map(this::mapToResponseDto);
    }

    public ProductResponseDto createProduct(ProductRequestDto dto) {
        Product product = new Product(
                null,
                dto.getName(),
                dto.getCategory(),
                dto.getPrice(),
                dto.getActive() != null ? dto.getActive() : true
        );

        Product saved = productRepository.save(product);
        return mapToResponseDto(saved);
    }

    public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto dto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }

        Product product = optionalProduct.get();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        if (dto.getActive() != null) {
            product.setActive(dto.getActive());
        }
        product.setUpdatedAt(LocalDateTime.now());

        Product updated = productRepository.save(product);
        return Optional.of(mapToResponseDto(updated));
    }

    public Optional<ProductResponseDto> updateStatus(Long id, boolean active) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }

        Product product = optionalProduct.get();
        product.setActive(active);
        product.setUpdatedAt(LocalDateTime.now());

        Product updated = productRepository.save(product);
        return Optional.of(mapToResponseDto(updated));
    }

    public boolean deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }

    private ProductResponseDto mapToResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.isActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
