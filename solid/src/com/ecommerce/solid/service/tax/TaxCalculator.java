package com.ecommerce.solid.service.tax;

import com.ecommerce.solid.model.OrderItem;
import java.util.List;

/**
 * Interface Segregation Principle (ISP) & Single Responsibility Principle (SRP):
 * Dedicated, lean interface focused exclusively on tax calculation for order items.
 */
@FunctionalInterface
public interface TaxCalculator {

    /**
     * Calculates the total tax amount for the given list of order items.
     *
     * @param items list of order items
     * @return total tax amount
     */
    double calculateTax(List<OrderItem> items);
}
