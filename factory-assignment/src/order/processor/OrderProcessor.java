package order.processor;

import order.model.Order;
import order.model.ProcessingResult;

/**
 * Common interface for all order category processors.
 * New order categories can implement this interface without modifying existing code.
 */
public interface OrderProcessor {
    /**
     * Returns the uppercase category identifier this processor handles (e.g. FOOD, CLOTHING, ELECTRONICS).
     */
    String getCategory();

    /**
     * Executes category-specific order processing logic.
     */
    ProcessingResult process(Order order);
}
