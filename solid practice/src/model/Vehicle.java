package model;

/**
 * Single Responsibility Principle (SRP):
 * Holds state and core properties of a vehicle. Persistence and business logic are kept in separate classes.
 * 
 * Liskov Substitution Principle (LSP):
 * Serves as base class. Any subclass can replace Vehicle without altering correctness.
 */
public abstract class Vehicle {
    private String id;
    private String brand;
    private String modelName;

    public Vehicle(String id, String brand, String modelName) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelName() {
        return modelName;
    }

    public abstract String getVehicleType();

    public void startEngine() {
        System.out.println(getBrand() + " " + getModelName() + " (" + getVehicleType() + ") engine started.");
    }
}
