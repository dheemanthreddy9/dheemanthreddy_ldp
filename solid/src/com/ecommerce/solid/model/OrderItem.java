package com.ecommerce.solid.model;

import com.ecommerce.solid.config.AppConfig;
import java.util.Objects;

public class OrderItem {

    private final String productId;
    private final String productName;
    private final String category;
    private final double unitPrice;
    private final int quantity;

    public OrderItem(String productId, String productName, String category, double unitPrice, int quantity) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.productId = Objects.requireNonNull(productId, "Product ID cannot be null");
        this.productName = Objects.requireNonNull(productName, "Product name cannot be null");
        this.category = Objects.requireNonNull(category, "Category cannot be null");
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return productName + " (x" + quantity + ") - " + AppConfig.CURRENCY_SYMBOL + getTotalPrice();
    }
}
