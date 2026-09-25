package model;

import interfaces.Rechargeable;

/**
 * Concrete Vehicle implementation.
 * LSP: Can replace Vehicle seamlessly anywhere in code.
 * ISP: Implements Rechargeable, avoiding forced refuel() method implementation.
 */
public class ElectricCar extends Vehicle implements Rechargeable {

    public ElectricCar(String id, String brand, String modelName) {
        super(id, brand, modelName);
    }

    @Override
    public String getVehicleType() {
        return "Electric Car";
    }

    @Override
    public void charge(double kwh) {
        System.out.println("Charging " + getBrand() + " " + getModelName() + " with " + kwh + " kWh of electric power.");
    }
}
