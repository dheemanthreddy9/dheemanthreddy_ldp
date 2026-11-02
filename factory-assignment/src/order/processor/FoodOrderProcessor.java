package order.processor;

import order.model.Order;
import order.model.ProcessingResult;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FoodOrderProcessor implements OrderProcessor {

    private static final double FOOD_TAX_RATE = 0.05;
    private static final double PACKAGING_FEE = 2.50;
    private static final double EXPRESS_DELIVERY_FEE = 4.00;

    @Override
    public String getCategory() {
        return "FOOD";
    }

    @Override
    public ProcessingResult process(Order order) {
        System.out.println("[FoodOrderProcessor] Validating perishable food items and kitchen dispatch for " + order.getOrderId());

        double subTotal = order.getSubTotal();
        double tax = subTotal * FOOD_TAX_RATE;
        double shipping = PACKAGING_FEE + EXPRESS_DELIVERY_FEE;
        String trackingId = "FOOD-EXP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        List<String> notes = Arrays.asList(
                "Temperature-controlled insulation bag applied",
                "Kitchen hygiene & freshness quality check passed",
                "Assigned to express rider (estimated delivery: 30-40 mins)",
                "Perishable item: Non-returnable once seal is broken"
        );

        return new ProcessingResult(
                order.getOrderId(),
                getCategory(),
                true,
                "Kitchen order prepared & dispatched via express delivery",
                subTotal,
                tax,
                shipping,
                trackingId,
                notes
        );
    }
}
