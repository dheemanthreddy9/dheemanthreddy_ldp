package interfaces;

/**
 * Interface Segregation Principle (ISP):
 * Clients should not be forced to depend on methods they do not use.
 * Only fuel-based vehicles (gasoline/diesel) implement this interface.
 */
public interface Refuelable {
    void refuel(double liters);
}
