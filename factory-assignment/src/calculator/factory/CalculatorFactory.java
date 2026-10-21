package calculator.factory;

import calculator.product.Calculator;

/**
 * Creator Abstract Class in the GoF Factory Method Design Pattern.
 *
 * Defines the Factory Method (createCalculator) which defers instantiation
 * of the specific calculator product (Basic, Scientific, Programmer) to subclasses.
 *
 * Also provides template methods that rely on the product created by the factory method.
 */
public abstract class CalculatorFactory {

    /**
     * The Factory Method.
     * Subclasses override this method to instantiate and return their specific Calculator product.
     *
     * @return Calculator product instance
     */
    public abstract Calculator createCalculator();

    /**
     * Template method that launches the calculator, displays its mode banner and UI layout.
     *
     * @return the launched Calculator instance
     */
    public Calculator launchCalculator() {
        Calculator calculator = createCalculator();
        System.out.println("=========================================================================");
        System.out.println("  LAUNCHING: " + calculator.getModeName());
        System.out.println("=========================================================================");
        calculator.displayLayout();
        return calculator;
    }
}
