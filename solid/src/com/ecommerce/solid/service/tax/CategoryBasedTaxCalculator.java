package com.ecommerce.solid.service.tax;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.OrderItem;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryBasedTaxCalculator implements TaxCalculator {

    private final Map<String, Double> categoryRates;
    private final double defaultTaxRate;

    public CategoryBasedTaxCalculator() {
        this(AppConfig.DEFAULT_GST_RATE);
        registerRate(AppConfig.CATEGORY_GROCERY, AppConfig.GROCERY_GST_RATE);
        registerRate(AppConfig.CATEGORY_ELECTRONICS, AppConfig.ELECTRONICS_GST_RATE);
    }

    public CategoryBasedTaxCalculator(double defaultTaxRate) {
        this.categoryRates = new HashMap<>();
        this.defaultTaxRate = defaultTaxRate;
    }

    public CategoryBasedTaxCalculator(Map<String, Double> initialRates, double defaultTaxRate) {
        this.categoryRates = new HashMap<>();
        this.defaultTaxRate = defaultTaxRate;
        if (initialRates != null) {
            initialRates.forEach(this::registerRate);
        }
    }

    public CategoryBasedTaxCalculator registerRate(String category, double rate) {
        if (category != null && rate >= 0.0) {
            categoryRates.put(category.toUpperCase(), rate);
        }
        return this;
    }

    public double getRateForCategory(String category) {
        if (category == null) {
            return defaultTaxRate;
        }
        return categoryRates.getOrDefault(category.toUpperCase(), defaultTaxRate);
    }

    public Map<String, Double> getCategoryRates() {
        return Collections.unmodifiableMap(categoryRates);
    }

    public double getDefaultTaxRate() {
        return defaultTaxRate;
    }

    @Override
    public double calculateTax(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        return items.stream()
                .mapToDouble(item -> item.getTotalPrice() * getRateForCategory(item.getCategory()))
                .sum();
    }
}
