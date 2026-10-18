package com.ecommerce.solid.service.tax;

import com.ecommerce.solid.model.OrderItem;
import java.util.List;

@FunctionalInterface
public interface TaxCalculator {
    double calculateTax(List<OrderItem> items);
}
