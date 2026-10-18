package com.ecommerce.solid.service.delivery;

@FunctionalInterface
public interface DeliveryFeeCalculator {
    double calculateDeliveryFee(double subtotal);
}
