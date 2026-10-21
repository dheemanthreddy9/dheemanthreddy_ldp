package calculator.product;

import java.util.List;

/**
 * Product Interface in the Factory Method Pattern.
 * Defines common behavior for all Calculator operational modes
 * (Basic, Scientific, Programmer) inspired by Windows and Ubuntu Calculators.
 */
public interface Calculator {
    /**
     * Returns the mode name (e.g., "Standard / Basic", "Scientific", "Programmer").
     */
    String getModeName();

    /**
     * Returns a list of supported operations for this calculator mode.
     */
    List<String> getSupportedOperations();

    /**
     * Displays the visual UI layout for this calculator mode in console.
     */
    void displayLayout();

    /**
     * Executes an operation with the given operands.
     *
     * @param operation operation name or operator symbol (e.g., "+", "sqrt", "sin", "AND", "HEX")
     * @param operands  input numbers
     * @return CalculationResult encapsulating the result and formatting
     */
    CalculationResult calculate(String operation, double... operands);

    /**
     * Displays help and examples of operations supported by this mode.
     */
    void printHelp();
}
