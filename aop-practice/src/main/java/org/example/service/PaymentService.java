package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String processPayment(String accountId, double amount) {
        System.out.println("  [BUSINESS LOGIC] Executing processPayment for Account: " + accountId + ", Amount: $" + amount);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "SUCCESS_TXN_" + System.currentTimeMillis();
    }

    public double withdrawMoney(String accountId, double amount) {
        System.out.println("  [BUSINESS LOGIC] Executing withdrawMoney for Account: " + accountId + ", Amount: $" + amount);
        return amount;
    }
}
