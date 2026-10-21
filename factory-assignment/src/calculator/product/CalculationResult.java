package calculator.product;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalculationResult {
    private final String expression;
    private final double numericValue;
    private final String formattedOutput;
    private final Map<String, String> representations;

    public CalculationResult(String expression, double numericValue, String formattedOutput) {
        this(expression, numericValue, formattedOutput, Collections.emptyMap());
    }

    public CalculationResult(String expression, double numericValue, String formattedOutput, Map<String, String> representations) {
        this.expression = expression;
        this.numericValue = numericValue;
        this.formattedOutput = formattedOutput;
        this.representations = new LinkedHashMap<>(representations);
    }

    public String getExpression() {
        return expression;
    }

    public double getNumericValue() {
        return numericValue;
    }

    public String getFormattedOutput() {
        return formattedOutput;
    }

    public Map<String, String> getRepresentations() {
        return Collections.unmodifiableMap(representations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Result: %s = %s", expression, formattedOutput));
        if (!representations.isEmpty()) {
            sb.append("\n  [Representations]:");
            for (Map.Entry<String, String> entry : representations.entrySet()) {
                sb.append(String.format("\n    %-4s : %s", entry.getKey(), entry.getValue()));
            }
        }
        return sb.toString();
    }
}
