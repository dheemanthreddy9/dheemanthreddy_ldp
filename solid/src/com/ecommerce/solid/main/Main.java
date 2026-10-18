package com.ecommerce.solid.main;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.Customer;
import com.ecommerce.solid.model.Order;
import com.ecommerce.solid.model.OrderItem;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.service.OrderService;
import com.ecommerce.solid.service.delivery.DeliveryFeeCalculator;
import com.ecommerce.solid.service.delivery.StandardDeliveryFeeCalculator;
import com.ecommerce.solid.service.notification.NotificationService;
import com.ecommerce.solid.service.notification.SmsNotificationService;
import com.ecommerce.solid.service.payment.PaymentProcessor;
import com.ecommerce.solid.service.payment.UpiPaymentProcessor;
import com.ecommerce.solid.service.receipt.ReceiptGenerator;
import com.ecommerce.solid.service.receipt.StandardReceiptGenerator;
import com.ecommerce.solid.service.tax.CategoryBasedTaxCalculator;
import com.ecommerce.solid.service.tax.TaxCalculator;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Dependency Inversion Principle (DIP): Instantiate dependencies and inject into OrderService
        TaxCalculator taxCalculator = new CategoryBasedTaxCalculator();
        DeliveryFeeCalculator deliveryFeeCalculator = new StandardDeliveryFeeCalculator();
        PaymentProcessor paymentProcessor = new UpiPaymentProcessor();
        NotificationService notificationService = new SmsNotificationService();
        ReceiptGenerator receiptGenerator = new StandardReceiptGenerator();

        OrderService orderService = new OrderService(
                taxCalculator,
                deliveryFeeCalculator,
                paymentProcessor,
                notificationService
        );

        Customer customer = new Customer(
                "CUST-001", 
                "Raj nick", 
                "raj@gmail.com", 
                AppConfig.COUNTRY_CODE_INDIA + "9876543210"
        );

        List<OrderItem> items = Arrays.asList(
                new OrderItem("P101", "Basmati Rice 5kg", AppConfig.CATEGORY_GROCERY, 450.00, 1),
                new OrderItem("P102", "Amul Butter 500g", AppConfig.CATEGORY_GROCERY, 275.00, 2),
                new OrderItem("P103", "Bluetooth Earbuds", AppConfig.CATEGORY_ELECTRONICS, 1499.00, 1)
        );

        Order order = orderService.createOrder("1", customer, items);

        PaymentDetails paymentDetails = new PaymentDetails(
                "raj@gmail.com", 
                AppConfig.PAYMENT_METHOD_UPI, 
                AppConfig.DEFAULT_CURRENCY, 
                order.calculateFinalTotal()
        );

        orderService.processPayment(order, paymentDetails);

        // Single Responsibility Principle (SRP): Receipt generation handled by dedicated ReceiptGenerator
        String receipt = receiptGenerator.generateReceipt(order);
        System.out.println(receipt);
    }
}
