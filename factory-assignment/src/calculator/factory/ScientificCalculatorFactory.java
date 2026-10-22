package calculator.factory;

import calculator.product.Calculator;
import calculator.product.ScientificCalculator;

public class ScientificCalculatorFactory extends CalculatorFactory {

    @Override
    public Calculator createCalculator() {
        return new ScientificCalculator();
    }
}
