package com.ecommerce.solid.model;

import java.util.Objects;

public class PaymentDetails {

    private final String paymentTokenOrAccount;
    private final String paymentMethod;
    private final String currency;
    private final double amount;

    public PaymentDetails(String paymentTokenOrAccount, String paymentMethod, String currency, double amount) {
        this.paymentTokenOrAccount = Objects.requireNonNull(paymentTokenOrAccount, "Payment token cannot be null");
        this.paymentMethod = Objects.requireNonNull(paymentMethod, "Payment method cannot be null");
        this.currency = Objects.requireNonNull(currency, "Currency cannot be null");
        this.amount = amount;
    }

    public String getPaymentTokenOrAccount() {
        return paymentTokenOrAccount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
