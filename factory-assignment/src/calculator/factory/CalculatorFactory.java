package calculator.factory;

import calculator.product.Calculator;

public abstract class CalculatorFactory {

    public abstract Calculator createCalculator();

    public Calculator launchCalculator() {
        Calculator calculator = createCalculator();
        System.out.println("=========================================================================");
        System.out.println("  LAUNCHING: " + calculator.getModeName());
        System.out.println("=========================================================================");
        calculator.displayLayout();
        return calculator;
    }
}
