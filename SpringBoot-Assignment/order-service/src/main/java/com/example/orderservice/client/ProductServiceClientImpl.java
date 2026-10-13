package com.example.orderservice.client;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.exception.ProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClientImpl implements ProductServiceClient {

    private final RestTemplate restTemplate;

    @Value("${product.service.url:http://localhost:8081/api/products}")
    private String productServiceUrl;

    @Autowired
    public ProductServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setProductServiceUrl(String productServiceUrl) {
        this.productServiceUrl = productServiceUrl;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        String url = productServiceUrl + "/" + productId;
        try {
            ProductDto product = restTemplate.getForObject(url, ProductDto.class);
            if (product == null) {
                throw new ProductServiceException("Product with ID " + productId + " could not be retrieved from Product Service");
            }
            return product;
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductServiceException("Product with ID " + productId + " does not exist in Product Service");
        } catch (Exception e) {
            if (e instanceof ProductServiceException) {
                throw e;
            }
            throw new ProductServiceException("Unable to communicate with Product Service: " + e.getMessage());
        }
    }
}
