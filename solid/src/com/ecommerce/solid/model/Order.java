package com.ecommerce.solid.model;

import com.ecommerce.solid.config.AppConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {

    private final String orderId;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private double taxAmount;
    private double deliveryFee;

    public Order(String orderId, Customer customer, List<OrderItem> items) {
        this.orderId = Objects.requireNonNull(orderId, "Order ID cannot be null");
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        this.items = new ArrayList<>(Objects.requireNonNull(items, "Items cannot be null"));
        this.orderStatus = OrderStatus.CREATED;
        this.paymentStatus = PaymentStatus.DECLINED;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double calculateSubtotal() {
        return items.stream()
                .mapToDouble(item -> item.getTotalPrice())
                .sum();
    }

    public double calculateFinalTotal() {
        return calculateSubtotal() + taxAmount + deliveryFee;
    }

    public String generateReceipt() {
        StringBuilder builder = new StringBuilder();
        builder.append("-----------------------------------------\n");
        builder.append("ORDER RECEIPT - ").append(orderId).append("\n");
        builder.append("Customer : ").append(customer.getFullName()).append("\n");
        builder.append("Phone    : ").append(customer.getPhoneNumber()).append("\n");
        builder.append("Status   : ").append(orderStatus).append("\n");
        builder.append("-----------------------------------------\n");
        items.forEach(item -> builder.append(item.toString()).append("\n"));
        builder.append("-----------------------------------------\n");
        builder.append("Subtotal     : ").append(AppConfig.CURRENCY_SYMBOL).append(String.format("%.2f", calculateSubtotal())).append(" ").append(AppConfig.DEFAULT_CURRENCY).append("\n");
        builder.append("GST Tax      : ").append(AppConfig.CURRENCY_SYMBOL).append(String.format("%.2f", taxAmount)).append("\n");
        builder.append("Delivery Fee : ").append(AppConfig.CURRENCY_SYMBOL).append(String.format("%.2f", deliveryFee)).append("\n");
        builder.append("TOTAL AMOUNT : ").append(AppConfig.CURRENCY_SYMBOL).append(String.format("%.2f", calculateFinalTotal())).append(" ").append(AppConfig.DEFAULT_CURRENCY).append("\n");
        builder.append("-----------------------------------------\n");
        return builder.toString();
    }
}
