package com.ecommerce.solid.service.payment;

import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;

/**
 * Interface Segregation Principle (ISP) & Single Responsibility Principle (SRP):
 * Standalone, decoupled interface dedicated to payment processing.
 * Clients using this interface are not forced to depend on OrderService or any unrelated logic.
 */
@FunctionalInterface
public interface PaymentProcessor {

    /**
     * Processes a payment transaction for the provided payment details.
     *
     * @param details payment information
     * @return PaymentStatus (SUCCESS, DECLINED, REFUNDED)
     */
    PaymentStatus processPayment(PaymentDetails details);
}
