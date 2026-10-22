package com.ecommerce.solid.service.payment;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.PaymentDetails;
import com.ecommerce.solid.model.PaymentStatus;

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
