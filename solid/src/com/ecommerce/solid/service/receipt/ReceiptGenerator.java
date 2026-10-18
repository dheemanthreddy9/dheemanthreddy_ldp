package com.ecommerce.solid.service.receipt;

import com.ecommerce.solid.model.Order;

/**
 * Interface Segregation Principle (ISP) & Single Responsibility Principle (SRP):
 * Dedicated interface for generating order receipts/invoices.
 * Separates presentation and formatting from the Order domain model.
 */
@FunctionalInterface
public interface ReceiptGenerator {

    /**
     * Generates a formatted receipt representation for an order.
     *
     * @param order the order to generate receipt for
     * @return formatted receipt string
     */
    String generateReceipt(Order order);
}
