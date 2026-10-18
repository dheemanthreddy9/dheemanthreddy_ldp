package com.ecommerce.solid.service.payment;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;

/**
 * Liskov Substitution Principle (LSP) & Open/Closed Principle (OCP):
 * Dedicated implementation of PaymentProcessor for UPI payments.
 * Can be substituted anywhere PaymentProcessor is expected without unexpected side-effects.
 */
public class UpiPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentStatus processPayment(PaymentDetails details) {
        if (details == null) {
            return PaymentStatus.DECLINED;
        }

        boolean isValidUpi = details.getPaymentTokenOrAccount() != null
                && details.getPaymentTokenOrAccount().contains("@")
                && details.getAmount() >= AppConfig.MINIMUM_ORDER_AMOUNT;

        return isValidUpi ? PaymentStatus.SUCCESS : PaymentStatus.DECLINED;
    }
}
