package calculator.factory;

import calculator.product.BasicCalculator;
import calculator.product.Calculator;

/**
 * Concrete Creator for Basic / Standard Calculator mode.
 * Overrides the factory method to return a BasicCalculator product.
 */
public class BasicCalculatorFactory extends CalculatorFactory {

    @Override
    public Calculator createCalculator() {
        return new BasicCalculator();
    }
}
