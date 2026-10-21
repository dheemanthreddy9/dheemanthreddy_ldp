package calculator.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete Product representing the Scientific Calculator mode
 * (equivalent to Windows Scientific Calculator or Ubuntu Advanced Mode).
 */
public class ScientificCalculator implements Calculator {

    private boolean degreeMode = true; // Default angle unit: Degrees (as in Windows Calculator)

    public boolean isDegreeMode() {
        return degreeMode;
    }

    public void setDegreeMode(boolean degreeMode) {
        this.degreeMode = degreeMode;
    }

    @Override
    public String getModeName() {
        return "Scientific Calculator [" + (degreeMode ? "DEG" : "RAD") + "]";
    }

    @Override
    public List<String> getSupportedOperations() {
        return Arrays.asList(
                "+", "-", "*", "/", "%", "sqrt", "1/x", "sqr", "neg",
                "sin", "cos", "tan", "asin", "acos", "atan",
                "log (base 10)", "ln (natural log)",
                "pow (x^y)", "exp (e^x)", "10^x", "cbrt",
                "fact (n!)", "abs", "pi", "e", "mode (toggle DEG/RAD)"
        );
    }

    @Override
    public void displayLayout() {
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("|                   WINDOWS / UBUNTU CALCULATOR                   |");
        System.out.printf("|                       [ SCIENTIFIC MODE - %-3s ]                   |%n", (degreeMode ? "DEG" : "RAD"));
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("|  [DEG/RAD]  [ sin ]   [ cos ]   [ tan ]   [ log ]   [ ln  ]  [ n! ] |");
        System.out.println("|  [  x^y  ]  [ asin]   [ acos]   [ atan]   [ 10^x]   [ e^x ]  [abs ] |");
        System.out.println("|  [ sqrt  ]  [ cbrt]   [ pi  ]   [  e  ]   [ CE  ]   [  C  ]  [DEL ] |");
        System.out.println("|  [   7   ]  [  8  ]   [  9  ]   [  /  ]   [ 1/x ]   [ sqr ]  [mod ] |");
        System.out.println("|  [   4   ]  [  5  ]   [  6  ]   [  *  ]   [  (  ]   [  )  ]  [ %  ] |");
        System.out.println("|  [   1   ]  [  2  ]   [  3  ]   [  -  ]                             |");
        System.out.println("|  [  +/-  ]  [  0  ]   [  .  ]   [  +  ]   [  =  ]                   |");
        System.out.println("+-----------------------------------------------------------------+");
    }

    @Override
    public CalculationResult calculate(String operation, double... operands) {
        if (operation == null || operation.trim().isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        String op = operation.trim().toUpperCase();

        // 1. Check basic arithmetic delegation
        try {
            BasicCalculator basic = new BasicCalculator();
            return basic.calculate(operation, operands);
        } catch (UnsupportedOperationException ignored) {
            // Not a basic operation, proceed with scientific operations
        }

        switch (op) {
            case "MODE":
                this.degreeMode = !this.degreeMode;
                String current = degreeMode ? "Degrees (DEG)" : "Radians (RAD)";
                return new CalculationResult("AngleMode", degreeMode ? 1 : 0, current);

            case "SIN":
                validateOperands(1, operands, "sin");
                double radSin = degreeMode ? Math.toRadians(operands[0]) : operands[0];
                double sinVal = Math.sin(radSin);
                return new CalculationResult("sin(" + operands[0] + (degreeMode ? "°" : " rad") + ")", sinVal, formatNumber(sinVal));

            case "COS":
                validateOperands(1, operands, "cos");
                double radCos = degreeMode ? Math.toRadians(operands[0]) : operands[0];
                // Handle 90° cosine precision artifact
                double cosVal = Math.abs(Math.cos(radCos)) < 1e-15 ? 0.0 : Math.cos(radCos);
                return new CalculationResult("cos(" + operands[0] + (degreeMode ? "°" : " rad") + ")", cosVal, formatNumber(cosVal));

            case "TAN":
                validateOperands(1, operands, "tan");
                double radTan = degreeMode ? Math.toRadians(operands[0]) : operands[0];
                if (Math.abs(Math.cos(radTan)) < 1e-15) {
                    throw new ArithmeticException("Tangent undefined at 90° / odd multiples of π/2");
                }
                double tanVal = Math.tan(radTan);
                return new CalculationResult("tan(" + operands[0] + (degreeMode ? "°" : " rad") + ")", tanVal, formatNumber(tanVal));

            case "ASIN":
                validateOperands(1, operands, "asin");
                if (operands[0] < -1.0 || operands[0] > 1.0) {
                    throw new IllegalArgumentException("Domain error for asin: operand must be between -1 and 1");
                }
                double asinVal = Math.asin(operands[0]);
                double finalAsin = degreeMode ? Math.toDegrees(asinVal) : asinVal;
                return new CalculationResult("asin(" + operands[0] + ")", finalAsin, formatNumber(finalAsin) + (degreeMode ? "°" : " rad"));

            case "ACOS":
                validateOperands(1, operands, "acos");
                if (operands[0] < -1.0 || operands[0] > 1.0) {
                    throw new IllegalArgumentException("Domain error for acos: operand must be between -1 and 1");
                }
                double acosVal = Math.acos(operands[0]);
                double finalAcos = degreeMode ? Math.toDegrees(acosVal) : acosVal;
                return new CalculationResult("acos(" + operands[0] + ")", finalAcos, formatNumber(finalAcos) + (degreeMode ? "°" : " rad"));

            case "ATAN":
                validateOperands(1, operands, "atan");
                double atanVal = Math.atan(operands[0]);
                double finalAtan = degreeMode ? Math.toDegrees(atanVal) : atanVal;
                return new CalculationResult("atan(" + operands[0] + ")", finalAtan, formatNumber(finalAtan) + (degreeMode ? "°" : " rad"));

            case "LOG":
            case "LOG10":
                validateOperands(1, operands, "log");
                if (operands[0] <= 0) {
                    throw new ArithmeticException("Logarithm undefined for non-positive numbers");
                }
                double logVal = Math.log10(operands[0]);
                return new CalculationResult("log10(" + operands[0] + ")", logVal, formatNumber(logVal));

            case "LN":
                validateOperands(1, operands, "ln");
                if (operands[0] <= 0) {
                    throw new ArithmeticException("Natural logarithm undefined for non-positive numbers");
                }
                double lnVal = Math.log(operands[0]);
                return new CalculationResult("ln(" + operands[0] + ")", lnVal, formatNumber(lnVal));

            case "POW":
            case "X^Y":
            case "^":
                validateOperands(2, operands, "pow");
                double powVal = Math.pow(operands[0], operands[1]);
                return new CalculationResult(operands[0] + " ^ " + operands[1], powVal, formatNumber(powVal));

            case "EXP":
            case "E^X":
                validateOperands(1, operands, "exp");
                double expVal = Math.exp(operands[0]);
                return new CalculationResult("e^(" + operands[0] + ")", expVal, formatNumber(expVal));

            case "10^X":
                validateOperands(1, operands, "10^x");
                double pow10 = Math.pow(10, operands[0]);
                return new CalculationResult("10^(" + operands[0] + ")", pow10, formatNumber(pow10));

            case "CBRT":
                validateOperands(1, operands, "cbrt");
                double cbrtVal = Math.cbrt(operands[0]);
                return new CalculationResult("cbrt(" + operands[0] + ")", cbrtVal, formatNumber(cbrtVal));

            case "FACT":
            case "!":
            case "N!":
                validateOperands(1, operands, "fact");
                if (operands[0] < 0 || operands[0] != Math.floor(operands[0])) {
                    throw new IllegalArgumentException("Factorial is only defined for non-negative integers");
                }
                if (operands[0] > 170) {
                    throw new ArithmeticException("Factorial overflow for n > 170 in double precision");
                }
                double factVal = factorial((int) operands[0]);
                return new CalculationResult(operands[0] + "!", factVal, formatNumber(factVal));

            case "ABS":
                validateOperands(1, operands, "abs");
                double absVal = Math.abs(operands[0]);
                return new CalculationResult("|" + operands[0] + "|", absVal, formatNumber(absVal));

            case "PI":
                return new CalculationResult("π", Math.PI, String.valueOf(Math.PI));

            case "E":
                return new CalculationResult("e", Math.E, String.valueOf(Math.E));

            default:
                throw new UnsupportedOperationException(
                        "Operation '" + operation + "' is not supported in Scientific mode. Supported: " + getSupportedOperations()
                );
        }
    }

    private double factorial(int n) {
        double result = 1.0;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private void validateOperands(int expected, double[] operands, String opName) {
        if (operands == null || operands.length < expected) {
            throw new IllegalArgumentException("Operation '" + opName + "' requires " + expected + " operand(s)");
        }
    }

    private String formatNumber(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return String.valueOf(value);
        }
        if (value == (long) value) {
            return String.format("%d", (long) value);
        }
        return String.format("%.8f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    @Override
    public void printHelp() {
        System.out.println("Scientific Calculator Usage:");
        System.out.println("  Trigonometry : 'sin', 'cos', 'tan', 'asin', 'acos', 'atan' (e.g. calculate(\"sin\", 30))");
        System.out.println("  Logarithms   : 'log', 'ln' (e.g. calculate(\"log\", 100))");
        System.out.println("  Powers       : 'pow' (e.g. calculate(\"pow\", 2, 8)), 'exp', '10^x', 'cbrt'");
        System.out.println("  Functions    : 'fact' (e.g. calculate(\"fact\", 5)), 'abs'");
        System.out.println("  Constants    : 'pi', 'e'");
        System.out.println("  Angle Mode   : 'mode' (toggle between DEG and RAD)");
    }
}
