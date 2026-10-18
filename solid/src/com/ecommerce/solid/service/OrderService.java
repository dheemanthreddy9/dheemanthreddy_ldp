package com.ecommerce.solid.service;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.Customer;
import com.ecommerce.solid.model.Order;
import com.ecommerce.solid.model.OrderItem;
import com.ecommerce.solid.model.OrderStatus;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;
import com.ecommerce.solid.service.delivery.DeliveryFeeCalculator;
import com.ecommerce.solid.service.delivery.StandardDeliveryFeeCalculator;
import com.ecommerce.solid.service.notification.NotificationService;
import com.ecommerce.solid.service.notification.SmsNotificationService;
import com.ecommerce.solid.service.payment.PaymentProcessor;
import com.ecommerce.solid.service.payment.UpiPaymentProcessor;
import com.ecommerce.solid.service.tax.CategoryBasedTaxCalculator;
import com.ecommerce.solid.service.tax.TaxCalculator;
import java.util.List;
import java.util.Objects;

/**
 * High-level orchestration service for Order management.
 * Follows SOLID principles:
 * - Single Responsibility Principle (SRP): Coordinates order workflow without implementing tax, delivery, payment, or notification algorithms directly.
 * - Open/Closed Principle (OCP): Open for extension by injecting new strategy implementations without modifying this class.
 * - Liskov Substitution Principle (LSP): Works transparently with any subtype conforming to the injected interfaces.
 * - Interface Segregation Principle (ISP): Depends on segregated, client-specific interfaces rather than fat monolithic interfaces.
 * - Dependency Inversion Principle (DIP): Depends strictly on abstractions (interfaces), not low-level concrete classes.
 */
public class OrderService {

    private final TaxCalculator taxCalculator;
    private final DeliveryFeeCalculator deliveryFeeCalculator;
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;

    /**
     * Dependency Inversion Principle (DIP):
     * Dependencies are injected via constructor. High-level OrderService depends on abstractions.
     *
     * @param taxCalculator calculator for item taxes
     * @param deliveryFeeCalculator calculator for delivery fees
     * @param paymentProcessor processor for payment transactions
     * @param notificationService service for dispatching notifications
     */
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

    /**
     * Default constructor providing standard configuration for convenience.
     */
    public OrderService() {
        this(
                new CategoryBasedTaxCalculator(),
                new StandardDeliveryFeeCalculator(),
                new UpiPaymentProcessor(),
                new SmsNotificationService()
        );
    }

    /**
     * Creates and initializes an order with calculated subtotal, tax, and delivery fees.
     *
     * @param orderId unique order identifier
     * @param customer customer placing the order
     * @param items list of items in the order
     * @return the initialized Order
     */
    public Order createOrder(String orderId, Customer customer, List<OrderItem> items) {
        Order order = new Order(orderId, customer, items);
        double subtotal = order.calculateSubtotal();
        double tax = taxCalculator.calculateTax(items);
        double deliveryFee = deliveryFeeCalculator.calculateDeliveryFee(subtotal);

        order.setTaxAmount(tax);
        order.setDeliveryFee(deliveryFee);
        return order;
    }

    /**
     * Processes payment for the order using the injected PaymentProcessor and notifies the customer.
     *
     * @param order the order being paid for
     * @param paymentDetails the payment transaction details
     * @return the updated Order with its new status
     */
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
