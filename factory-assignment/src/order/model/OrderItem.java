package order.model;

public class OrderItem {
    private final String itemName;
    private final int quantity;
    private final double unitPrice;

    public OrderItem(String itemName, int quantity, double unitPrice) {
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getLineTotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%s (x%d) @ $%.2f = $%.2f", itemName, quantity, unitPrice, getLineTotal());
    }
}
