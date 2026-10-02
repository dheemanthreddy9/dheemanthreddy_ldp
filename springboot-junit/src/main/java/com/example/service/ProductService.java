package com.example.service;

import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                return product;
            }
        }
        return null;
    }

    public String deleteProduct(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                products.remove(product);
                return "Product deleted successfully";
            }
        }
        return "Product not found";
    }

    public void clearProducts() {
        products.clear();
    }
}
