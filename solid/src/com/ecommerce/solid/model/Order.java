package com.ecommerce.solid.model;

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
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public double calculateFinalTotal() {
        return calculateSubtotal() + taxAmount + deliveryFee;
    }
}
