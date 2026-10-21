package order;

import order.factory.OnlineOrderProcessingFactory;
import order.model.Order;
import order.model.OrderItem;
import order.model.ProcessingResult;
import order.processor.OrderProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("========================================================================");
        System.out.println("   ONLINE ORDER PROCESSING SYSTEM - FACTORY PATTERN (OCP COMPLIANT)    ");
        System.out.println("========================================================================");
        System.out.println("Discovered Categories via Factory: " + OnlineOrderProcessingFactory.getSupportedCategories());
        System.out.println();

        List<Order> incomingOrders = createSampleOrders();

        for (int i = 0; i < incomingOrders.size(); i++) {
            Order order = incomingOrders.get(i);
            System.out.printf("[%d/%d] Processing incoming order: %s (Category: %s)...%n",
                    i + 1, incomingOrders.size(), order.getOrderId(), order.getCategory());

            try {

                OrderProcessor processor = OnlineOrderProcessingFactory.getProcessor(order.getCategory());
                ProcessingResult result = processor.process(order);
                result.printReceipt();
            } catch (UnsupportedOperationException e) {
                System.err.println("❌ ERROR: " + e.getMessage());
            }

            System.out.println();
        }

        System.out.println("========================================================================");
        System.out.println("   OPEN-CLOSED PRINCIPLE (OCP) DEMONSTRATION SUMMARY                    ");
        System.out.println("========================================================================");
        System.out.println("1. FoodOrderProcessor handles FOOD orders.");
        System.out.println("2. ClothingOrderProcessor handles CLOTHING orders.");
        System.out.println("3. ElectronicsOrderProcessor was introduced as a NEW order category.");
        System.out.println("   * OnlineOrderProcessingFactory.java was NOT edited (Closed for modification).");
        System.out.println("   * Main.java was NOT edited (Client code remains unchanged).");
        System.out.println("   * Only the new processor class was implemented and registered (Open for extension).");
        System.out.println("========================================================================");
    }

    private static List<Order> createSampleOrders() {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(
                "ORD-FD-101",
                "Arun Varma",
                "FOOD",
                Arrays.asList(
                        new OrderItem("Hyderabadi Chicken Biryani", 2, 14.50),
                        new OrderItem("Garlic Butter Naan", 4, 2.50),
                        new OrderItem("Mango Lassi", 2, 3.50)
                ),
                "Flat 402, Green Meadows, Hyderabad"
        ));

        orders.add(new Order(
                "ORD-CL-202",
                "Sneha Rao",
                "CLOTHING",
                Arrays.asList(
                        new OrderItem("Slim-fit Denim Jacket", 1, 55.00),
                        new OrderItem("Cotton Crew-neck T-shirt", 2, 18.00)
                ),
                "House #12, Indiranagar, Bangalore"
        ));

        orders.add(new Order(
                "ORD-EL-303",
                "Vikram Malhotra",
                "ELECTRONICS",
                Arrays.asList(
                        new OrderItem("Noise-Cancelling Wireless Headphones", 1, 199.99),
                        new OrderItem("Braided USB-C Fast Charger Cable", 2, 15.00)
                ),
                "Tech Park Avenue, Gachibowli, Hyderabad"
        ));

        return orders;
    }
}
