package com.ecommerce.solid.service.delivery;

import com.ecommerce.solid.config.AppConfig;

public class StandardDeliveryFeeCalculator implements DeliveryFeeCalculator {

    private final double freeDeliveryThreshold;
    private final double standardDeliveryFee;

    public StandardDeliveryFeeCalculator() {
        this(AppConfig.FREE_DELIVERY_THRESHOLD, AppConfig.STANDARD_DELIVERY_FEE);
    }

    public StandardDeliveryFeeCalculator(double freeDeliveryThreshold, double standardDeliveryFee) {
        this.freeDeliveryThreshold = freeDeliveryThreshold;
        this.standardDeliveryFee = standardDeliveryFee;
    }

    public double getFreeDeliveryThreshold() {
        return freeDeliveryThreshold;
    }

    public double getStandardDeliveryFee() {
        return standardDeliveryFee;
    }

    @Override
    public double calculateDeliveryFee(double subtotal) {
        return subtotal >= freeDeliveryThreshold ? 0.0 : standardDeliveryFee;
    }
}
