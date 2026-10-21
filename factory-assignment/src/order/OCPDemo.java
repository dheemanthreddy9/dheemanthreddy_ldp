package order;

import order.factory.OnlineOrderProcessingFactory;
import order.model.Order;
import order.model.OrderItem;
import order.model.ProcessingResult;
import order.processor.OrderProcessor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Dedicated demonstration showing that:
 * 1. OnlineOrderProcessingFactory is CLOSED for modification.
 * 2. Main is CLOSED for modification.
 * 3. Adding a new category (e.g., Grocery) is OPEN for extension.
 */
public class OCPDemo {

    public static void main(String[] args) {
        System.out.println("========================================================================");
        System.out.println("     STEP-BY-STEP OPEN-CLOSED PRINCIPLE (OCP) VERIFICATION TEST         ");
        System.out.println("========================================================================");

        // Step 1: Query factory for initially loaded categories
        System.out.println("[Step 1] Initial categories loaded in factory:");
        System.out.println("         " + OnlineOrderProcessingFactory.getSupportedCategories());

        // Step 2: Attempt to process an order for a new category that is not yet added
        Order groceryOrder = new Order(
                "ORD-GR-901",
                "Kavita Reddy",
                "GROCERY",
                Arrays.asList(
                        new OrderItem("Organic Almond Milk 1L", 2, 4.25),
                        new OrderItem("Rolled Oats 1kg", 1, 6.50)
                ),
                "Jubilee Hills, Hyderabad"
        );

        System.out.println("\n[Step 2] Attempting to process GROCERY order before adding GroceryOrderProcessor:");
        try {
            OrderProcessor processor = OnlineOrderProcessingFactory.getProcessor(groceryOrder.getCategory());
            processor.process(groceryOrder);
        } catch (UnsupportedOperationException e) {
            System.out.println("  EXPECTED RESULT -> " + e.getMessage());
        }

        // Step 3: Now, we add GroceryOrderProcessor (Open for extension)
        // Notice: OnlineOrderProcessingFactory.java source code is NOT edited!
        System.out.println("\n[Step 3] Introducing GroceryOrderProcessor dynamically without touching factory source code...");
        OrderProcessor groceryProcessor = new GroceryOrderProcessor();
        OnlineOrderProcessingFactory.registerProcessor(groceryProcessor);

        // Step 4: Re-query factory
        System.out.println("\n[Step 4] Categories supported by factory now:");
        System.out.println("         " + OnlineOrderProcessingFactory.getSupportedCategories());

        // Step 5: Process the exact same GROCERY order
        System.out.println("\n[Step 5] Processing GROCERY order again:");
        OrderProcessor processor = OnlineOrderProcessingFactory.getProcessor(groceryOrder.getCategory());
        ProcessingResult result = processor.process(groceryOrder);
        result.printReceipt();

        System.out.println("\n SUCCESS: New order category added cleanly with 0 edits to OnlineOrderProcessingFactory!");
    }

    /**
     * Newly added order processor class demonstrating OCP extension.
     */
    static class GroceryOrderProcessor implements OrderProcessor {
        @Override
        public String getCategory() {
            return "GROCERY";
        }

        @Override
        public ProcessingResult process(Order order) {
            System.out.println("[GroceryOrderProcessor] Packing perishable groceries in thermal bags for " + order.getOrderId());
            double subTotal = order.getSubTotal();
            double tax = subTotal * 0.02; // 2% grocery essential tax
            double shipping = 3.00; // Slot delivery fee
            String trackingId = "GROC-SLOT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            List<String> notes = Arrays.asList(
                    "Packed in biodegradable bags",
                    "Morning delivery slot (7:00 AM - 9:00 AM) scheduled"
            );
            return new ProcessingResult(order.getOrderId(), getCategory(), true,
                    "Groceries packed and assigned to morning delivery van",
                    subTotal, tax, shipping, trackingId, notes);
        }
    }
}
