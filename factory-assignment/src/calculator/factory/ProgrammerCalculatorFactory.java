package calculator.factory;

import calculator.product.Calculator;
import calculator.product.ProgrammerCalculator;

public class ProgrammerCalculatorFactory extends CalculatorFactory {

    @Override
    public Calculator createCalculator() {
        return new ProgrammerCalculator();
    }
}
