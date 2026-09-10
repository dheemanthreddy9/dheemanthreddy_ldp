package strategies;

import interfaces.FuelStrategy;

/**
 * Open/Closed Principle (OCP):
 * Concrete strategy for calculating petrol fuel cost based on distance.
 */
public class PetrolFuelStrategy implements FuelStrategy {
    private static final double COST_PER_KM = 0.12; // $0.12 per km

    @Override
    public double calculateCost(double distanceKm) {
        return distanceKm * COST_PER_KM;
    }
}
