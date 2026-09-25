package interfaces;

/**
 * Open/Closed Principle (OCP):
 * Software entities should be open for extension, but closed for modification.
 * Adding new fuel calculation types is done by creating new implementations of this interface.
 */
public interface FuelStrategy {
    double calculateCost(double distanceKm);
}
