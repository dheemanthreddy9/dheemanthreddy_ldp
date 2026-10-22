package com.example.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OrderRequestDto {

    @NotBlank(message = "Customer name is mandatory")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String customerName;

    @NotNull(message = "Product ID is mandatory")
    @Positive(message = "Product ID must be greater than zero")
    private Long productId;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public OrderRequestDto() {
    }

    public OrderRequestDto(String customerName, Long productId, Integer quantity) {
        this.customerName = customerName;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
