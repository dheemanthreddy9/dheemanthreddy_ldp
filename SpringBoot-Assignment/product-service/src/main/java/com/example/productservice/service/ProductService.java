package com.example.productservice.service;

import com.example.productservice.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
}
