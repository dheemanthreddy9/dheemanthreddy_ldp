package com.ecommerce.solid.service;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.Customer;
import com.ecommerce.solid.model.Order;
import com.ecommerce.solid.model.OrderItem;
import com.ecommerce.solid.model.OrderStatus;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class OrderService {

    @FunctionalInterface
    public interface PaymentProcessor {
        PaymentStatus processPayment(PaymentDetails details);
    }

    @FunctionalInterface
    public interface TaxCalculator {
        double calculateTax(List<OrderItem> items);
    }

    private final TaxCalculator gstCalculator = items -> items.stream()
            .mapToDouble(item -> {
                if (AppConfig.CATEGORY_GROCERY.equalsIgnoreCase(item.getCategory())) {
                    return item.getTotalPrice() * AppConfig.GROCERY_GST_RATE;
                } else if (AppConfig.CATEGORY_ELECTRONICS.equalsIgnoreCase(item.getCategory())) {
                    return item.getTotalPrice() * AppConfig.ELECTRONICS_GST_RATE;
                }
                return item.getTotalPrice() * AppConfig.DEFAULT_GST_RATE;
            })
            .sum();

    private final DoubleUnaryOperator deliveryFeeCalculator = subtotal -> 
            subtotal >= AppConfig.FREE_DELIVERY_THRESHOLD ? 0.0 : AppConfig.STANDARD_DELIVERY_FEE;

    private final Predicate<PaymentDetails> upiValidator = details -> 
            details != null && details.getPaymentTokenOrAccount().contains("@") && details.getAmount() >= AppConfig.MINIMUM_ORDER_AMOUNT;

    private final BiConsumer<Customer, String> smsNotification = (customer, message) -> 
            System.out.println("[" + AppConfig.DEFAULT_SMS_SENDER_ID + " SMS to " + customer.getPhoneNumber() + "]: " + message);

    public Order createOrder(String orderId, Customer customer, List<OrderItem> items) {
        Order order = new Order(orderId, customer, items);
        double subtotal = order.calculateSubtotal();
        double tax = gstCalculator.calculateTax(items);
        double deliveryFee = deliveryFeeCalculator.applyAsDouble(subtotal);

        order.setTaxAmount(tax);
        order.setDeliveryFee(deliveryFee);
        return order;
    }

    public Order processPayment(Order order, PaymentDetails paymentDetails) {
        PaymentProcessor upiProcessor = details -> 
                upiValidator.test(details) ? PaymentStatus.SUCCESS : PaymentStatus.DECLINED;

        PaymentStatus status = upiProcessor.processPayment(paymentDetails);
        order.setPaymentStatus(status);

        if (status == PaymentStatus.SUCCESS) {
            order.setOrderStatus(OrderStatus.PAID);
            smsNotification.accept(
                    order.getCustomer(), 
                    "Payment of " + AppConfig.CURRENCY_SYMBOL + String.format("%.2f", order.calculateFinalTotal()) + " " + AppConfig.DEFAULT_CURRENCY + " received for Order " + order.getOrderId()
            );
        } else {
            order.setOrderStatus(OrderStatus.FAILED);
        }
        return order;
    }
}
