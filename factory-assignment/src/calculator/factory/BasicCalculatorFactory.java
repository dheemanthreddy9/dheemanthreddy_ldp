package calculator.factory;

import calculator.product.BasicCalculator;
import calculator.product.Calculator;

public class BasicCalculatorFactory extends CalculatorFactory {

    @Override
    public Calculator createCalculator() {
        return new BasicCalculator();
    }
}
