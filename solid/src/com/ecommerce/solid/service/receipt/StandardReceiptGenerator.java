package com.ecommerce.solid.service.receipt;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.Order;

public class StandardReceiptGenerator implements ReceiptGenerator {

    @Override
    public String generateReceipt(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        StringBuilder builder = new StringBuilder();
        builder.append("-----------------------------------------\n");
        builder.append("ORDER RECEIPT - ").append(order.getOrderId()).append("\n");
        builder.append("Customer : ").append(order.getCustomer().getFullName()).append("\n");
        builder.append("Phone    : ").append(order.getCustomer().getPhoneNumber()).append("\n");
        builder.append("Status   : ").append(order.getOrderStatus()).append("\n");
        builder.append("-----------------------------------------\n");
        order.getItems().forEach(item -> builder.append(item.toString()).append("\n"));
        builder.append("-----------------------------------------\n");
        builder.append("Subtotal     : ").append(AppConfig.CURRENCY_SYMBOL)
                .append(String.format("%.2f", order.calculateSubtotal())).append(" ")
                .append(AppConfig.DEFAULT_CURRENCY).append("\n");
        builder.append("GST Tax      : ").append(AppConfig.CURRENCY_SYMBOL)
                .append(String.format("%.2f", order.getTaxAmount())).append("\n");
        builder.append("Delivery Fee : ").append(AppConfig.CURRENCY_SYMBOL)
                .append(String.format("%.2f", order.getDeliveryFee())).append("\n");
        builder.append("TOTAL AMOUNT : ").append(AppConfig.CURRENCY_SYMBOL)
                .append(String.format("%.2f", order.calculateFinalTotal())).append(" ")
                .append(AppConfig.DEFAULT_CURRENCY).append("\n");
        builder.append("-----------------------------------------\n");
        return builder.toString();
    }
}
