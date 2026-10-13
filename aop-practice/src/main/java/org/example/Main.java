package org.example;

import org.example.config.AppConfig;
import org.example.service.PaymentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        System.out.println("           SPRING AOP              ");


        // Initialize Spring ApplicationContext with Java Config
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            // Retrieve Spring Proxy Bean for PaymentService
            PaymentService paymentService = context.getBean(PaymentService.class);

            System.out.println("\n Invoking Method 1: processPayment");
            String status = paymentService.processPayment("ACC-98765", 499.99);

            System.out.println("\n Invoking Method 2: withdrawMoney");
            double amount = paymentService.withdrawMoney("ACC-98765", 150.00);

            System.out.println("Execution Completed Successfully!");
        }
    }
}
