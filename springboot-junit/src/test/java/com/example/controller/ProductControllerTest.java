package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(productService);
    }

    @Test
    @DisplayName("Should create product and return 201 Created")
    void createProductTest() {
        Product product = new Product(1L, "Laptop", "Electronics", 999.99, 10);

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        var response = productController.createProduct(product);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Laptop", response.getBody().getName());

        verify(productService, times(1)).createProduct(product);
    }

    @Test
    @DisplayName("Should return all products with 200 OK")
    void getAllProductsTest() {
        List<Product> products = List.of(
                new Product(1L, "Laptop", "Electronics", 999.99, 10),
                new Product(2L, "Smartphone", "Electronics", 699.99, 25)
        );

        when(productService.getAllProducts()).thenReturn(products);

        var response = productController.getAllProducts();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());

        verify(productService).getAllProducts();
    }

    @Test
    @DisplayName("Should get product by ID")
    void getProductByIdTest() {
        Product product = new Product(1L, "Laptop", "Electronics", 999.99, 10);

        when(productService.getProductById(1L)).thenReturn(product);

        var response = productController.getProductById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Laptop", response.getBody().getName());

        verify(productService).getProductById(1L);
    }

    @Test
    @DisplayName("Should return 404 when product is not found")
    void getProductByIdNotFoundTest() {
        when(productService.getProductById(99L)).thenReturn(null);

        var response = productController.getProductById(99L);

        assertEquals(404, response.getStatusCode().value());

        verify(productService).getProductById(99L);
    }

    @Test
    @DisplayName("Should update product successfully")
    void updateProductTest() {
        Product product = new Product(1L, "Updated Laptop", "Electronics", 1199.99, 8);

        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(product);

        var response = productController.updateProduct(1L, product);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Updated Laptop", response.getBody().getName());

        verify(productService).updateProduct(eq(1L), any(Product.class));
    }

    @Test
    @DisplayName("Should delete product successfully")
    void deleteProductTest() {
        when(productService.deleteProduct(1L)).thenReturn("Product deleted successfully");

        var response = productController.deleteProduct(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Product deleted successfully", response.getBody());

        verify(productService).deleteProduct(1L);
    }

    @Test
    @DisplayName("Should return 404 when deleting non-existent product")
    void deleteProductNotFoundTest() {
        when(productService.deleteProduct(99L)).thenReturn("Product not found");

        var response = productController.deleteProduct(99L);

        assertEquals(404, response.getStatusCode().value());

        verify(productService).deleteProduct(99L);
    }

    @Test
    @DisplayName("Should verify service is never called with invalid ID")
    void verifyNeverTest() {
        when(productService.getProductById(1L)).thenReturn(null);

        productController.getProductById(1L);

        verify(productService, never()).getProductById(99L);
    }

    @Test
    @DisplayName("Should capture product passed to service")
    void argumentCaptorTest() {
        Product product = new Product(1L, "Headphones", "Audio", 149.99, 15);

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        productController.createProduct(product);

        verify(productService).createProduct(productCaptor.capture());

        Product capturedProduct = productCaptor.getValue();
        assertEquals("Headphones", capturedProduct.getName());
        assertEquals("Audio", capturedProduct.getCategory());
    }
}
