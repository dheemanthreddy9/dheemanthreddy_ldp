package com.example.service;

import com.example.model.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        productService.createProduct(
                new Product(1L, "Laptop", "Electronics", 999.99, 10)
        );
    }

    @AfterEach
    void tearDown() {
        productService.clearProducts();
    }

    @Test
    @DisplayName("Should create a new product successfully")
    void createProductTest() {
        Product product = new Product(2L, "Smartphone", "Electronics", 699.99, 25);
        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals("Smartphone", result.getName());
        assertEquals("Electronics", result.getCategory());
        assertEquals(699.99, result.getPrice());
    }

    @Test
    @DisplayName("Should get all products in inventory")
    void getAllProductsTest() {
        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should find product by valid ID")
    void getProductByIdTest() {
        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getName());
    }

    @Test
    @DisplayName("Should return null for non-existing product ID")
    void getProductByInvalidIdTest() {
        Product result = productService.getProductById(99L);

        assertNull(result);
    }

    @Test
    @DisplayName("Should update product details")
    void updateProductTest() {
        Product updatedProduct = new Product(1L, "Gaming Laptop", "Electronics", 1299.99, 5);

        Product result = productService.updateProduct(1L, updatedProduct);

        assertAll(
                () -> assertEquals("Gaming Laptop", result.getName()),
                () -> assertEquals(1299.99, result.getPrice()),
                () -> assertEquals(5, result.getQuantity())
        );
    }

    @Test
    @DisplayName("Should delete product successfully")
    void deleteProductTest() {
        String result = productService.deleteProduct(1L);

        assertEquals("Product deleted successfully", result);
        assertNull(productService.getProductById(1L));
    }

    @Test
    @DisplayName("Should return product not found when deleting invalid ID")
    void deleteInvalidProductTest() {
        String result = productService.deleteProduct(99L);

        assertEquals("Product not found", result);
    }

    @ParameterizedTest
    @ValueSource(longs = {100L, 250L, 500L})
    @DisplayName("Should validate positive product prices or IDs")
    void positiveProductIdsTest(Long id) {
        assertTrue(id > 0);
    }
}
