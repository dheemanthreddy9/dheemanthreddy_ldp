package com.ecommerce.solid.service;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.Customer;
import com.ecommerce.solid.model.Order;
import com.ecommerce.solid.model.OrderItem;
import com.ecommerce.solid.model.OrderStatus;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;
import com.ecommerce.solid.service.delivery.DeliveryFeeCalculator;
import com.ecommerce.solid.service.notification.NotificationService;
import com.ecommerce.solid.service.payment.PaymentProcessor;
import com.ecommerce.solid.service.tax.TaxCalculator;
import java.util.List;
import java.util.Objects;

public class OrderService {

    private final TaxCalculator taxCalculator;
    private final DeliveryFeeCalculator deliveryFeeCalculator;
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;

    public OrderService(
            TaxCalculator taxCalculator,
            DeliveryFeeCalculator deliveryFeeCalculator,
            PaymentProcessor paymentProcessor,
            NotificationService notificationService
    ) {
        this.taxCalculator = Objects.requireNonNull(taxCalculator, "TaxCalculator cannot be null");
        this.deliveryFeeCalculator = Objects.requireNonNull(deliveryFeeCalculator, "DeliveryFeeCalculator cannot be null");
        this.paymentProcessor = Objects.requireNonNull(paymentProcessor, "PaymentProcessor cannot be null");
        this.notificationService = Objects.requireNonNull(notificationService, "NotificationService cannot be null");
    }

    public Order createOrder(String orderId, Customer customer, List<OrderItem> items) {
        Order order = new Order(orderId, customer, items);
        double subtotal = order.calculateSubtotal();
        double tax = taxCalculator.calculateTax(items);
        double deliveryFee = deliveryFeeCalculator.calculateDeliveryFee(subtotal);

        order.setTaxAmount(tax);
        order.setDeliveryFee(deliveryFee);
        return order;
    }

    public Order processPayment(Order order, PaymentDetails paymentDetails) {
        PaymentStatus status = paymentProcessor.processPayment(paymentDetails);
        order.setPaymentStatus(status);

        if (status == PaymentStatus.SUCCESS) {
            order.setOrderStatus(OrderStatus.PAID);
            notificationService.sendNotification(
                    order.getCustomer(),
                    "Payment of " + AppConfig.CURRENCY_SYMBOL 
                            + String.format("%.2f", order.calculateFinalTotal()) 
                            + " " + AppConfig.DEFAULT_CURRENCY 
                            + " received for Order " + order.getOrderId()
            );
        } else {
            order.setOrderStatus(OrderStatus.FAILED);
        }
        return order;
    }
}
