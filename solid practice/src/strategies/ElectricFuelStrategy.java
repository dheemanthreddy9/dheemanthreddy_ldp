package strategies;

import interfaces.FuelStrategy;

/**
 * Open/Closed Principle (OCP):
 * Concrete strategy for calculating electric charging cost based on distance.
 */
public class ElectricFuelStrategy implements FuelStrategy {
    private static final double COST_PER_KM = 0.04; // $0.04 per km

    @Override
    public double calculateCost(double distanceKm) {
        return distanceKm * COST_PER_KM;
    }
}
