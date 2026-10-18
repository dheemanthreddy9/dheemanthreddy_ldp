package com.ecommerce.solid.service.payment;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;

/**
 * Liskov Substitution Principle (LSP) & Open/Closed Principle (OCP):
 * Dedicated implementation of PaymentProcessor for Net Banking payments.
 */
public class NetBankingPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentStatus processPayment(PaymentDetails details) {
        if (details == null) {
            return PaymentStatus.DECLINED;
        }

        boolean isValid = details.getPaymentTokenOrAccount() != null
                && !details.getPaymentTokenOrAccount().trim().isEmpty()
                && details.getAmount() >= AppConfig.MINIMUM_ORDER_AMOUNT;

        return isValid ? PaymentStatus.SUCCESS : PaymentStatus.DECLINED;
    }
}
