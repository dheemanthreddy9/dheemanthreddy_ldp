import model.Order;
import notification.Notification;
import notification.SmsNotification;
import payment.Payment;
import payment.UpiPayment;
import service.OrderService;

public class Main {

    public static void main(String[] args) {

        Payment payment = new UpiPayment();

        Notification notification = new SmsNotification();

        OrderService orderService = new OrderService(payment, notification);

        Order order = new Order(500);

        orderService.placeOrder(order);
    }
}