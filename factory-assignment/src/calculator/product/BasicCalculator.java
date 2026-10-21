package calculator.product;

import java.util.Arrays;
import java.util.List;

public class BasicCalculator implements Calculator {

    @Override
    public String getModeName() {
        return "Standard / Basic Calculator";
    }

    @Override
    public List<String> getSupportedOperations() {
        return Arrays.asList(
                "+ (ADD)", "- (SUB)", "* (MUL)", "/ (DIV)",
                "% (PERCENT/MOD)", "sqrt (Square Root)",
                "1/x (Reciprocal)", "sqr (Square)", "neg (+/- Negate)"
        );
    }

    @Override
    public void displayLayout() {
        System.out.println("+---------------------------------------+");
        System.out.println("|       WINDOWS / UBUNTU CALCULATOR     |");
        System.out.println("|           [ STANDARD MODE ]           |");
        System.out.println("+---------------------------------------+");
        System.out.println("|   [ % ]     [ CE ]     [ C ]    [DEL] |");
        System.out.println("|  [ 1/x ]   [ x^2 ]    [sqrt]    [ / ] |");
        System.out.println("|  [  7  ]   [  8  ]    [  9 ]    [ * ] |");
        System.out.println("|  [  4  ]   [  5  ]    [  6 ]    [ - ] |");
        System.out.println("|  [  1  ]   [  2  ]    [  3 ]    [ + ] |");
        System.out.println("|  [ +/- ]   [  0  ]    [  . ]    [ = ] |");
        System.out.println("+---------------------------------------+");
    }

    @Override
    public CalculationResult calculate(String operation, double... operands) {
        if (operation == null || operation.trim().isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        String op = operation.trim().toUpperCase();

        switch (op) {
            case "+":
            case "ADD":
                validateOperands(2, operands, "+");
                return new CalculationResult(
                        operands[0] + " + " + operands[1],
                        operands[0] + operands[1],
                        formatNumber(operands[0] + operands[1])
                );

            case "-":
            case "SUB":
                validateOperands(2, operands, "-");
                return new CalculationResult(
                        operands[0] + " - " + operands[1],
                        operands[0] - operands[1],
                        formatNumber(operands[0] - operands[1])
                );

            case "*":
            case "MUL":
                validateOperands(2, operands, "*");
                return new CalculationResult(
                        operands[0] + " * " + operands[1],
                        operands[0] * operands[1],
                        formatNumber(operands[0] * operands[1])
                );

            case "/":
            case "DIV":
                validateOperands(2, operands, "/");
                if (operands[1] == 0.0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return new CalculationResult(
                        operands[0] + " / " + operands[1],
                        operands[0] / operands[1],
                        formatNumber(operands[0] / operands[1])
                );

            case "%":
            case "MOD":
                validateOperands(2, operands, "%");
                if (operands[1] == 0.0) {
                    throw new ArithmeticException("Cannot divide by zero for modulo");
                }
                return new CalculationResult(
                        operands[0] + " % " + operands[1],
                        operands[0] % operands[1],
                        formatNumber(operands[0] % operands[1])
                );

            case "SQRT":
                validateOperands(1, operands, "sqrt");
                if (operands[0] < 0) {
                    throw new ArithmeticException("Cannot calculate square root of a negative number in basic mode");
                }
                return new CalculationResult(
                        "sqrt(" + operands[0] + ")",
                        Math.sqrt(operands[0]),
                        formatNumber(Math.sqrt(operands[0]))
                );

            case "1/X":
            case "RECIPROCAL":
                validateOperands(1, operands, "1/x");
                if (operands[0] == 0.0) {
                    throw new ArithmeticException("Cannot divide by zero (1/0)");
                }
                return new CalculationResult(
                        "1/(" + operands[0] + ")",
                        1.0 / operands[0],
                        formatNumber(1.0 / operands[0])
                );

            case "SQR":
            case "SQUARE":
                validateOperands(1, operands, "sqr");
                return new CalculationResult(
                        "sqr(" + operands[0] + ")",
                        operands[0] * operands[0],
                        formatNumber(operands[0] * operands[0])
                );

            case "NEG":
            case "+/-":
                validateOperands(1, operands, "neg");
                return new CalculationResult(
                        "negate(" + operands[0] + ")",
                        -operands[0],
                        formatNumber(-operands[0])
                );

            case "PERCENT":

                if (operands.length == 1) {
                    return new CalculationResult(operands[0] + "%", operands[0] / 100.0, formatNumber(operands[0] / 100.0));
                } else if (operands.length == 2) {
                    double val = (operands[0] * operands[1]) / 100.0;
                    return new CalculationResult(operands[0] + "% of " + operands[1], val, formatNumber(val));
                } else {
                    throw new IllegalArgumentException("Operation 'PERCENT' expects 1 or 2 operands");
                }

            default:
                throw new UnsupportedOperationException(
                        "Operation '" + operation + "' is not supported in Standard Basic mode. Supported: " + getSupportedOperations()
                );
        }
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
        return String.format("%.6f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    @Override
    public void printHelp() {
        System.out.println("Basic Calculator Usage:");
        System.out.println("  Binary operations : '+', '-', '*', '/', '%' (e.g. calculate(\"+\", 12, 5))");
        System.out.println("  Unary operations  : 'sqrt', '1/x', 'sqr', 'neg' (e.g. calculate(\"sqrt\", 144))");
    }
}
