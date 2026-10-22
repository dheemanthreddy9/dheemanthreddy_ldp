package com.example.orderservice.client;

import com.example.orderservice.dto.ProductDto;

public interface ProductServiceClient {
    ProductDto getProductById(Long productId);
}
