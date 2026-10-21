package calculator.factory;

import calculator.product.Calculator;
import calculator.product.ScientificCalculator;

/**
 * Concrete Creator for Scientific Calculator mode.
 * Overrides the factory method to return a ScientificCalculator product.
 */
public class ScientificCalculatorFactory extends CalculatorFactory {

    @Override
    public Calculator createCalculator() {
        return new ScientificCalculator();
    }
}
