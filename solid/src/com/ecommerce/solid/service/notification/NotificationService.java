package com.ecommerce.solid.service.notification;

import com.ecommerce.solid.model.Customer;

@FunctionalInterface
public interface NotificationService {
    void sendNotification(Customer customer, String message);
}
