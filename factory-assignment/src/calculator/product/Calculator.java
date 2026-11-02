package calculator.product;

import java.util.List;

public interface Calculator {

    String getModeName();

    List<String> getSupportedOperations();

    void displayLayout();

    CalculationResult calculate(String operation, double... operands);

    void printHelp();
}
