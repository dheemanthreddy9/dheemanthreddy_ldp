package com.ecommerce.solid.main;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.Customer;
import com.ecommerce.solid.model.Order;
import com.ecommerce.solid.model.OrderItem;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.service.OrderService;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();

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

        Consumer<Order> receiptPrinter = ord -> System.out.println(ord.generateReceipt());
        receiptPrinter.accept(order);
    }
}
