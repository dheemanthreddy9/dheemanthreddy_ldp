package com.ecommerce.solid.service.payment;

import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;

@FunctionalInterface
public interface PaymentProcessor {
    PaymentStatus processPayment(PaymentDetails details);
}
