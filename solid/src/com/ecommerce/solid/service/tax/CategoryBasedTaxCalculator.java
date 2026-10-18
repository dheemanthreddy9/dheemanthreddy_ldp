package com.ecommerce.solid.service.tax;

import com.ecommerce.solid.config.AppConfig;
import com.ecommerce.solid.model.OrderItem;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Open/Closed Principle (OCP):
 * Closed for modification, but open for extension.
 * New categories and their corresponding tax rates can be registered dynamically at runtime
 * without having to modify this class or its callers with hardcoded if-else ladders.
 */
public class CategoryBasedTaxCalculator implements TaxCalculator {

    private final Map<String, Double> categoryRates;
    private final double defaultTaxRate;

    /**
     * Constructs a calculator pre-configured with default standard rates.
     */
    public CategoryBasedTaxCalculator() {
        this(AppConfig.DEFAULT_GST_RATE);
        registerRate(AppConfig.CATEGORY_GROCERY, AppConfig.GROCERY_GST_RATE);
        registerRate(AppConfig.CATEGORY_ELECTRONICS, AppConfig.ELECTRONICS_GST_RATE);
    }

    /**
     * Constructs a calculator with a customized default tax rate.
     *
     * @param defaultTaxRate fallback tax rate for unregistered categories
     */
    public CategoryBasedTaxCalculator(double defaultTaxRate) {
        this.categoryRates = new HashMap<>();
        this.defaultTaxRate = defaultTaxRate;
    }

    /**
     * Constructs a calculator with explicit category rates and default fallback rate.
     *
     * @param initialRates initial map of category to tax rate
     * @param defaultTaxRate fallback tax rate for unregistered categories
     */
    public CategoryBasedTaxCalculator(Map<String, Double> initialRates, double defaultTaxRate) {
        this.categoryRates = new HashMap<>();
        this.defaultTaxRate = defaultTaxRate;
        if (initialRates != null) {
            initialRates.forEach(this::registerRate);
        }
    }

    /**
     * Registers or updates a category tax rate without modifying existing code (OCP).
     *
     * @param category product category name
     * @param rate GST tax rate (e.g., 0.05 for 5%)
     * @return this calculator instance for fluent chaining
     */
    public CategoryBasedTaxCalculator registerRate(String category, double rate) {
        if (category != null && rate >= 0.0) {
            categoryRates.put(category.toUpperCase(), rate);
        }
        return this;
    }

    /**
     * Resolves the tax rate for a specific category.
     *
     * @param category category name
     * @return registered tax rate, or default fallback rate if not registered
     */
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
