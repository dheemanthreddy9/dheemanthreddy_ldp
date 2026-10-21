package order.processor;

import order.model.Order;
import order.model.ProcessingResult;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Concrete processor for Electronics orders.
 * DEMONSTRATION OF OPEN-CLOSED PRINCIPLE (OCP):
 * This class was added to the system as a brand new order category WITHOUT modifying
 * OnlineOrderProcessingFactory.java or Main.java!
 */
public class ElectronicsOrderProcessor implements OrderProcessor {

    private static final double ELECTRONICS_TAX_RATE = 0.18; // 18% Electronics tax
    private static final double TRANSIT_INSURANCE_AND_CRATING = 12.00;
    private static final double SECURE_COURIER_FEE = 8.50;

    @Override
    public String getCategory() {
        return "ELECTRONICS";
    }

    @Override
    public ProcessingResult process(Order order) {
        System.out.println("[ElectronicsOrderProcessor] Registering device serial numbers and warranty for " + order.getOrderId());

        double subTotal = order.getSubTotal();
        double tax = subTotal * ELECTRONICS_TAX_RATE;
        double shipping = TRANSIT_INSURANCE_AND_CRATING + SECURE_COURIER_FEE;
        String trackingId = "ELEC-SECURE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String serialNumber = "SN-" + Math.abs(order.getOrderId().hashCode()) + "-2026";

        List<String> notes = Arrays.asList(
                "Device Serial Number logged: " + serialNumber,
                "1-Year Comprehensive Manufacturer Warranty activated",
                "Electrostatic discharge (ESD) & shock-proof packaging applied",
                "Full transit value insurance ($" + TRANSIT_INSURANCE_AND_CRATING + ") included",
                "OTP verification required upon doorstep delivery"
        );

        return new ProcessingResult(
                order.getOrderId(),
                getCategory(),
                true,
                "Device tested, insured, and handed to armored courier partner",
                subTotal,
                tax,
                shipping,
                trackingId,
                notes
        );
    }
}
