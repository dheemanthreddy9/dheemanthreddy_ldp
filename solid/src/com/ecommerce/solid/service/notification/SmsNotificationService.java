package com.ecommerce.solid.service.notification;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.Customer;

/**
 * Single Responsibility Principle (SRP) & Liskov Substitution Principle (LSP):
 * SMS implementation of NotificationService.
 */
public class SmsNotificationService implements NotificationService {

    private final String senderId;

    public SmsNotificationService() {
        this(AppConfig.DEFAULT_SMS_SENDER_ID);
    }

    public SmsNotificationService(String senderId) {
        this.senderId = senderId != null ? senderId : AppConfig.DEFAULT_SMS_SENDER_ID;
    }

    public String getSenderId() {
        return senderId;
    }

    @Override
    public void sendNotification(Customer customer, String message) {
        if (customer == null) {
            return;
        }
        System.out.println("[" + senderId + " SMS to " + customer.getPhoneNumber() + "]: " + message);
    }
}
