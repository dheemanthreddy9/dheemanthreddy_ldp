package com.example.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is mandatory")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String customerName;

    @NotNull(message = "Product ID is mandatory")
    @Positive(message = "Product ID must be greater than zero")
    @Column(nullable = false)
    private Long productId;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private Integer quantity;

    @Positive(message = "Total price must be greater than zero")
    @Column(nullable = false)
    private BigDecimal totalPrice;

    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;

    public Order() {
    }

    public Order(Long id, String customerName, Long productId, Integer quantity, BigDecimal totalPrice, LocalDateTime orderDate, String status) {
        this.id = id;
        this.customerName = customerName;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(customerName, order.customerName) &&
                Objects.equals(productId, order.productId) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, productId, quantity, totalPrice, status);
    }
}
