package order.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String orderId;
    private final String customerName;
    private final String category;
    private final List<OrderItem> items;
    private final String shippingAddress;

    public Order(String orderId, String customerName, String category, List<OrderItem> items, String shippingAddress) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty");
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        this.orderId = orderId;
        this.customerName = customerName;
        this.category = category.trim().toUpperCase();
        this.items = new ArrayList<>(items);
        this.shippingAddress = shippingAddress != null ? shippingAddress : "Standard Address";
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCategory() {
        return category;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public double getSubTotal() {
        return items.stream().mapToDouble(OrderItem::getLineTotal).sum();
    }

    @Override
    public String toString() {
        return String.format("Order[ID=%s, Customer=%s, Category=%s, Items=%d, Subtotal=$%.2f]",
                orderId, customerName, category, items.size(), getSubTotal());
    }
}
