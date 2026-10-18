package com.ecommerce.solid.service.notification;

import com.ecommerce.solid.model.Customer;

/**
 * Interface Segregation Principle (ISP) & Single Responsibility Principle (SRP):
 * Segregated interface for sending customer notifications.
 */
@FunctionalInterface
public interface NotificationService {

    /**
     * Dispatches a notification to the specified customer.
     *
     * @param customer recipient customer
     * @param message notification content
     */
    void sendNotification(Customer customer, String message);
}
