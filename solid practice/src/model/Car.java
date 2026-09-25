package model;

import interfaces.Refuelable;

/**
 * Concrete Vehicle implementation.
 * LSP: Safely extends Vehicle without breaking contracts.
 * ISP: Implements Refuelable because petrol cars accept liquid fuel.
 */
public class Car extends Vehicle implements Refuelable {

    public Car(String id, String brand, String modelName) {
        super(id, brand, modelName);
    }

    @Override
    public String getVehicleType() {
        return "Petrol Car";
    }

    @Override
    public void refuel(double liters) {
        System.out.println("Refueling " + getBrand() + " " + getModelName() + " with " + liters + " liters of petrol.");
    }
}
