package service;

import model.Order;
import notification.Notification;
import payment.Payment;

public class OrderService {

    private final Payment payment;
    private final Notification notification;

    public OrderService(Payment payment, Notification notification) {
        this.payment = payment;
        this.notification = notification;
    }

    public void placeOrder(Order order) {
        if (order.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid order amount");
        }

        payment.pay(order.getAmount());
        notification.send("Order placed successfully!");
    }
}
