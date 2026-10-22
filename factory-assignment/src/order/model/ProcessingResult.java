package order.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessingResult {
    private final String orderId;
    private final String category;
    private final boolean success;
    private final String statusMessage;
    private final double subTotal;
    private final double taxAmount;
    private final double shippingFee;
    private final double finalPayable;
    private final String trackingNumber;
    private final List<String> specialHandlingNotes;

    public ProcessingResult(String orderId, String category, boolean success, String statusMessage,
                            double subTotal, double taxAmount, double shippingFee,
                            String trackingNumber, List<String> specialHandlingNotes) {
        this.orderId = orderId;
        this.category = category;
        this.success = success;
        this.statusMessage = statusMessage;
        this.subTotal = subTotal;
        this.taxAmount = taxAmount;
        this.shippingFee = shippingFee;
        this.finalPayable = subTotal + taxAmount + shippingFee;
        this.trackingNumber = trackingNumber;
        this.specialHandlingNotes = specialHandlingNotes != null
                ? new ArrayList<>(specialHandlingNotes)
                : Collections.emptyList();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCategory() {
        return category;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public double getFinalPayable() {
        return finalPayable;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public List<String> getSpecialHandlingNotes() {
        return Collections.unmodifiableList(specialHandlingNotes);
    }

    public void printReceipt() {
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("| ORDER RECEIPT - %-38s |%n", orderId);
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("| Category      : %-38s |%n", category);
        System.out.printf("| Status        : %-38s |%n", (success ? "CONFIRMED" : "FAILED") + " (" + statusMessage + ")");
        System.out.printf("| Subtotal      : $%-37.2f |%n", subTotal);
        System.out.printf("| Category Tax  : $%-37.2f |%n", taxAmount);
        System.out.printf("| Shipping Fee  : $%-37.2f |%n", shippingFee);
        System.out.printf("| Total Payable : $%-37.2f |%n", finalPayable);
        System.out.printf("| Tracking ID   : %-38s |%n", trackingNumber);
        if (!specialHandlingNotes.isEmpty()) {
            System.out.println("| Category Specific Handling:                            |");
            for (String note : specialHandlingNotes) {
                System.out.printf("|  * %-51s |%n", note);
            }
        }
        System.out.println("+--------------------------------------------------------+");
    }
}
