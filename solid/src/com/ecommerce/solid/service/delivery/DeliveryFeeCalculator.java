package com.ecommerce.solid.service.delivery;

/**
 * Interface Segregation Principle (ISP) & Single Responsibility Principle (SRP):
 * Dedicated interface for calculating delivery charges based on order subtotal.
 */
@FunctionalInterface
public interface DeliveryFeeCalculator {

    /**
     * Calculates the delivery fee for a given order subtotal.
     *
     * @param subtotal the order subtotal amount
     * @return applicable delivery fee
     */
    double calculateDeliveryFee(double subtotal);
}
