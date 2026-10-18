package com.ecommerce.solid.service.notification;

import com.ecommerce.solid.model.Customer;

/**
 * Single Responsibility Principle (SRP) & Liskov Substitution Principle (LSP):
 * Email implementation of NotificationService.
 */
public class EmailNotificationService implements NotificationService {

    @Override
    public void sendNotification(Customer customer, String message) {
        if (customer == null) {
            return;
        }
        System.out.println("[Email to " + customer.getEmail() + "]: " + message);
    }
}
