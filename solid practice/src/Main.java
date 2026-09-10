import interfaces.FuelStrategy;
import interfaces.Rechargeable;
import interfaces.Refuelable;
import interfaces.VehicleRepository;
import model.Car;
import model.ElectricCar;
import model.Vehicle;
import repository.InMemoryVehicleRepository;
import service.VehicleService;
import strategies.ElectricFuelStrategy;
import strategies.PetrolFuelStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SOLID PRINCIPLES DEMONSTRATION IN JAVA");
        System.out.println("==================================================\n");

        // 1. Dependency Inversion Principle (DIP):
        // High-level service depends on abstraction (VehicleRepository interface).
        VehicleRepository repo = new InMemoryVehicleRepository();
        VehicleService vehicleService = new VehicleService(repo);

        // 2. Single Responsibility Principle (SRP) & Liskov Substitution Principle (LSP):
        // Instantiate specific vehicles extending abstract Vehicle.
        Car petrolCar = new Car("V-001", "Toyota", "Camry");
        ElectricCar tesla = new ElectricCar("V-002", "Tesla", "Model 3");

        System.out.println("--- 1. Registering Vehicles (SRP & DIP) ---");
        vehicleService.registerVehicle(petrolCar);
        vehicleService.registerVehicle(tesla);

        // 3. Liskov Substitution Principle (LSP):
        // Subtypes (Car, ElectricCar) replace Vehicle seamlessly.
        vehicleService.startAllVehicles();

        // 4. Interface Segregation Principle (ISP):
        // Interfaces are segregated: petrolCar uses Refuelable, tesla uses Rechargeable.
        System.out.println("\n--- 2. Vehicle Operations (ISP) ---");
        Refuelable gasVehicle = petrolCar;
        gasVehicle.refuel(45.0);

        Rechargeable evVehicle = tesla;
        evVehicle.charge(75.0);

        // 5. Open/Closed Principle (OCP):
        // Calculate trip costs using polymorphic strategies without changing VehicleService logic.
        System.out.println("\n--- 3. Trip Cost Calculation (OCP) ---");
        double tripDistance = 150.0; // km

        FuelStrategy petrolStrategy = new PetrolFuelStrategy();
        FuelStrategy electricStrategy = new ElectricFuelStrategy();

        double petrolCost = vehicleService.calculateTripCost(tripDistance, petrolStrategy);
        double electricCost = vehicleService.calculateTripCost(tripDistance, electricStrategy);

        System.out.printf("150 km trip cost with Petrol Car (%s %s): $%.2f%n", petrolCar.getBrand(), petrolCar.getModelName(), petrolCost);
        System.out.printf("150 km trip cost with Electric Car (%s %s): $%.2f%n", tesla.getBrand(), tesla.getModelName(), electricCost);

        System.out.println("\n==================================================");
        System.out.println("   SUCCESS: ALL 5 SOLID PRINCIPLES DEMONSTRATED!");
        System.out.println("==================================================");
    }
}
