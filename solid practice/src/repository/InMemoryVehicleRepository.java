package repository;

import interfaces.VehicleRepository;
import model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Single Responsibility Principle (SRP):
 * Dedicated solely to storing and fetching Vehicle data.
 * 
 * Dependency Inversion Principle (DIP):
 * Implements VehicleRepository interface so higher-level services depend on abstract VehicleRepository.
 */
public class InMemoryVehicleRepository implements VehicleRepository {
    private final Map<String, Vehicle> database = new HashMap<>();

    @Override
    public void save(Vehicle vehicle) {
        database.put(vehicle.getId(), vehicle);
        System.out.println("[DB] Saved vehicle: " + vehicle.getBrand() + " " + vehicle.getModelName() + " (ID: " + vehicle.getId() + ")");
    }

    @Override
    public Vehicle findById(String id) {
        return database.get(id);
    }

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(database.values());
    }
}
