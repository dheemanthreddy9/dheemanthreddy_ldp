package com.ecommerce.solid.service.notification;

import com.ecommerce.solid.model.Customer;

public class EmailNotificationService implements NotificationService {

    @Override
    public void sendNotification(Customer customer, String message) {
        if (customer == null) {
            return;
        }
        System.out.println("[Email to " + customer.getEmail() + "]: " + message);
    }
}
