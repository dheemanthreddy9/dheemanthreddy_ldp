package com.ecommerce.solid.service.receipt;

import com.ecommerce.solid.model.Order;

@FunctionalInterface
public interface ReceiptGenerator {
    String generateReceipt(Order order);
}
