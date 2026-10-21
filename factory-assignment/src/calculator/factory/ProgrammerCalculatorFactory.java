package calculator.factory;

import calculator.product.Calculator;
import calculator.product.ProgrammerCalculator;

/**
 * Concrete Creator for Programmer Calculator mode.
 * Overrides the factory method to return a ProgrammerCalculator product.
 */
public class ProgrammerCalculatorFactory extends CalculatorFactory {

    @Override
    public Calculator createCalculator() {
        return new ProgrammerCalculator();
    }
}
