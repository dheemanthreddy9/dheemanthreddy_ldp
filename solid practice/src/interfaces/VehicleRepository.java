package interfaces;

import model.Vehicle;
import java.util.List;

/**
 * Dependency Inversion Principle (DIP):
 * High-level modules should depend on abstractions (interfaces), not concrete implementations.
 */
public interface VehicleRepository {
    void save(Vehicle vehicle);
    Vehicle findById(String id);
    List<Vehicle> findAll();
}
