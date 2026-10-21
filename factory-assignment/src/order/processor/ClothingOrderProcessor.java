package order.processor;

import order.model.Order;
import order.model.ProcessingResult;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Concrete processor for Clothing & Apparel orders.
 */
public class ClothingOrderProcessor implements OrderProcessor {

    private static final double APPAREL_TAX_RATE = 0.12; // 12% GST/VAT
    private static final double STANDARD_SHIPPING = 6.00;
    private static final double FREE_SHIPPING_THRESHOLD = 50.00;

    @Override
    public String getCategory() {
        return "CLOTHING";
    }

    @Override
    public ProcessingResult process(Order order) {
        System.out.println("[ClothingOrderProcessor] Verifying sizes, apparel tags, and warehouse stock for " + order.getOrderId());

        double subTotal = order.getSubTotal();
        double tax = subTotal * APPAREL_TAX_RATE;
        double shipping = subTotal >= FREE_SHIPPING_THRESHOLD ? 0.0 : STANDARD_SHIPPING;
        String trackingId = "CLOTH-LOGIX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        List<String> notes = Arrays.asList(
                "Apparel quality & size inspection completed",
                "Folded in moisture-proof eco-packaging",
                "30-day easy return barcode tag attached",
                subTotal >= FREE_SHIPPING_THRESHOLD
                        ? "Qualified for Free Standard Apparel Shipping"
                        : "Standard Ground Courier Shipping ($" + STANDARD_SHIPPING + ")"
        );

        return new ProcessingResult(
                order.getOrderId(),
                getCategory(),
                true,
                "Apparel packaged and scheduled for warehouse ground dispatch",
                subTotal,
                tax,
                shipping,
                trackingId,
                notes
        );
    }
}
