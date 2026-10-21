package calculator;

import calculator.factory.BasicCalculatorFactory;
import calculator.factory.CalculatorFactory;
import calculator.factory.ProgrammerCalculatorFactory;
import calculator.factory.ScientificCalculatorFactory;
import calculator.product.CalculationResult;
import calculator.product.Calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("--interactive")) {
            runInteractiveSession();
            return;
        }

        runAutomatedDemonstration();
    }

    public static void runAutomatedDemonstration() {
        System.out.println("=========================================================================");
        System.out.println("    WINDOWS & UBUNTU MULTI-MODE CALCULATOR (FACTORY METHOD PATTERN)     ");
        System.out.println("=========================================================================");

        CalculatorFactory basicFactory = new BasicCalculatorFactory();
        Calculator basicCalc = basicFactory.launchCalculator();

        System.out.println("\n--- [Basic Mode Calculations] ---");
        performAndPrint(basicCalc, "+", 450, 125);
        performAndPrint(basicCalc, "-", 1000, 345);
        performAndPrint(basicCalc, "*", 25, 16);
        performAndPrint(basicCalc, "/", 144, 12);
        performAndPrint(basicCalc, "%", 29, 6);
        performAndPrint(basicCalc, "sqrt", 169);
        performAndPrint(basicCalc, "1/x", 8);
        performAndPrint(basicCalc, "PERCENT", 15, 200);

        System.out.println("\n");
        CalculatorFactory scientificFactory = new ScientificCalculatorFactory();
        Calculator scientificCalc = scientificFactory.launchCalculator();

        System.out.println("\n--- [Scientific Mode Calculations] ---");
        performAndPrint(scientificCalc, "sin", 30);
        performAndPrint(scientificCalc, "cos", 60);
        performAndPrint(scientificCalc, "tan", 45);
        performAndPrint(scientificCalc, "log", 1000);
        performAndPrint(scientificCalc, "ln", Math.E);
        performAndPrint(scientificCalc, "pow", 2, 10);
        performAndPrint(scientificCalc, "exp", 2);
        performAndPrint(scientificCalc, "fact", 6);
        performAndPrint(scientificCalc, "cbrt", 27);
        performAndPrint(scientificCalc, "pi");
        performAndPrint(scientificCalc, "e");

        System.out.println("\nSwitching angle mode to Radians...");
        scientificCalc.calculate("mode");
        performAndPrint(scientificCalc, "sin", Math.PI / 2);

        System.out.println("\n");
        CalculatorFactory programmerFactory = new ProgrammerCalculatorFactory();
        Calculator programmerCalc = programmerFactory.launchCalculator();

        System.out.println("\n--- [Programmer Mode Calculations (HEX / DEC / OCT / BIN)] ---");

        System.out.println("[Radix Display for Value 42]:");
        performAndPrint(programmerCalc, "DISP", 42);

        System.out.println("\n[Radix Display for Value 255]:");
        performAndPrint(programmerCalc, "DISP", 255);

        System.out.println("\n[Bitwise Logic & Shift Operations]:");
        performAndPrint(programmerCalc, "AND", 0x0F, 0x33);
        performAndPrint(programmerCalc, "OR", 0xF0, 0x0F);
        performAndPrint(programmerCalc, "XOR", 0xFF, 0x0F);
        performAndPrint(programmerCalc, "LSH", 1, 8);
        performAndPrint(programmerCalc, "RSH", 256, 4);

        System.out.println("\n[Word Size Switching in Programmer Mode]:");
        System.out.println("Current: " + programmerCalc.getModeName());
        performAndPrint(programmerCalc, "WORDSIZE");
        System.out.println("Current: " + programmerCalc.getModeName());
        performAndPrint(programmerCalc, "DISP", 127);
        performAndPrint(programmerCalc, "NOT", 0);

        System.out.println("\n=========================================================================");
        System.out.println("  FACTORY METHOD DESIGN PATTERN IMPLEMENTATION COMPLETE                  ");
        System.out.println("  - Creator: CalculatorFactory (declares createCalculator)");
        System.out.println("  - Concrete Creators: BasicCalculatorFactory, ScientificCalculatorFactory,");
        System.out.println("                       ProgrammerCalculatorFactory");
        System.out.println("  - Product: Calculator (BasicCalculator, ScientificCalculator, ProgrammerCalculator)");
        System.out.println("=========================================================================");
    }

    private static void performAndPrint(Calculator calc, String op, double... operands) {
        try {
            CalculationResult result = calc.calculate(op, operands);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("  Error executing " + op + ": " + e.getMessage());
        }
    }

    private static void runInteractiveSession() {
        Scanner scanner = new Scanner(System.in);
        Calculator currentCalculator = new BasicCalculatorFactory().launchCalculator();

        while (true) {
            System.out.println("\n[Current Mode: " + currentCalculator.getModeName() + "]");
            System.out.println("Select Option:");
            System.out.println("  1. Switch to Basic Mode (BasicCalculatorFactory)");
            System.out.println("  2. Switch to Scientific Mode (ScientificCalculatorFactory)");
            System.out.println("  3. Switch to Programmer Mode (ProgrammerCalculatorFactory)");
            System.out.println("  4. View Supported Operations & Help");
            System.out.println("  5. Perform Calculation");
            System.out.println("  6. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();
            if ("6".equals(choice) || "exit".equalsIgnoreCase(choice)) {
                System.out.println("Exiting Calculator. Goodbye!");
                break;
            }

            switch (choice) {
                case "1":
                    currentCalculator = new BasicCalculatorFactory().launchCalculator();
                    break;
                case "2":
                    currentCalculator = new ScientificCalculatorFactory().launchCalculator();
                    break;
                case "3":
                    currentCalculator = new ProgrammerCalculatorFactory().launchCalculator();
                    break;
                case "4":
                    currentCalculator.printHelp();
                    break;
                case "5":
                    System.out.print("Enter operation (e.g. +, *, sqrt, sin, AND, LSH, DISP): ");
                    String op = scanner.nextLine().trim();
                    System.out.print("Enter operand 1: ");
                    double op1 = Double.parseDouble(scanner.nextLine().trim());
                    if (op.equalsIgnoreCase("sqrt") || op.equalsIgnoreCase("sin") || op.equalsIgnoreCase("cos")
                            || op.equalsIgnoreCase("tan") || op.equalsIgnoreCase("log") || op.equalsIgnoreCase("ln")
                            || op.equalsIgnoreCase("fact") || op.equalsIgnoreCase("NOT") || op.equalsIgnoreCase("DISP")
                            || op.equalsIgnoreCase("1/x") || op.equalsIgnoreCase("sqr") || op.equalsIgnoreCase("neg")) {
                        performAndPrint(currentCalculator, op, op1);
                    } else {
                        System.out.print("Enter operand 2: ");
                        double op2 = Double.parseDouble(scanner.nextLine().trim());
                        performAndPrint(currentCalculator, op, op1, op2);
                    }
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }
}
