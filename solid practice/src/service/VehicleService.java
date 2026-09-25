package service;

import interfaces.FuelStrategy;
import interfaces.VehicleRepository;
import model.Vehicle;

import java.util.List;

/**
 * Single Responsibility Principle (SRP):
 * High-level coordinator for managing vehicle operations and trips.
 * 
 * Dependency Inversion Principle (DIP):
 * Injects VehicleRepository abstraction instead of instantiating concrete repository directly.
 */
public class VehicleService {
    private final VehicleRepository repository;

    // Dependency Injection via constructor (DIP)
    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public void registerVehicle(Vehicle vehicle) {
        repository.save(vehicle);
    }

    public void startAllVehicles() {
        System.out.println("\n--- Starting All Vehicles (LSP Demonstration) ---");
        List<Vehicle> vehicles = repository.findAll();
        for (Vehicle v : vehicles) {
            // Polymorphic call - works for Car, ElectricCar, or any future Vehicle subclass
            v.startEngine();
        }
    }

    public double calculateTripCost(double distanceKm, FuelStrategy strategy) {
        // Open/Closed Principle (OCP): Works with any FuelStrategy without code modification
        return strategy.calculateCost(distanceKm);
    }
}
