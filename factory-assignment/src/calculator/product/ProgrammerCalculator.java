package calculator.product;

import java.util.*;

public class ProgrammerCalculator implements Calculator {

    public enum WordSize {
        BYTE(8, 0xFFL, "BYTE (8-bit)"),
        WORD(16, 0xFFFFL, "WORD (16-bit)"),
        DWORD(32, 0xFFFFFFFFL, "DWORD (32-bit)"),
        QWORD(64, -1L, "QWORD (64-bit)");

        private final int bits;
        private final long mask;
        private final String label;

        WordSize(int bits, long mask, String label) {
            this.bits = bits;
            this.mask = mask;
            this.label = label;
        }

        public int getBits() {
            return bits;
        }

        public long getMask() {
            return mask;
        }

        public String getLabel() {
            return label;
        }
    }

    private WordSize currentWordSize = WordSize.QWORD;

    public WordSize getCurrentWordSize() {
        return currentWordSize;
    }

    public void setCurrentWordSize(WordSize wordSize) {
        if (wordSize != null) {
            this.currentWordSize = wordSize;
        }
    }

    @Override
    public String getModeName() {
        return "Programmer Calculator [" + currentWordSize.name() + "]";
    }

    @Override
    public List<String> getSupportedOperations() {
        return Arrays.asList(
                "HEX/DEC/OCT/BIN (Multi-base display)",
                "AND, OR, XOR, NOT (Bitwise Logic)",
                "LSH (Left Shift), RSH (Right Shift)",
                "ROL (Rotate Left), ROR (Rotate Right)",
                "+, -, *, /, % (Integer Arithmetic)",
                "WORDSIZE (Toggle BYTE/WORD/DWORD/QWORD)"
        );
    }

    @Override
    public void displayLayout() {
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("|                   WINDOWS / UBUNTU CALCULATOR                   |");
        System.out.printf("|                      [ PROGRAMMER MODE - %-5s ]                 |%n", currentWordSize.name());
        System.out.println("+-----------------------------------------------------------------+");
        System.out.printf("| Word Size: %-51s  |%n", currentWordSize.getLabel());
        System.out.println("| Radixes  : HEX (Base 16) | DEC (Base 10) | OCT (Base 8) | BIN (Base 2)|");
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("|  [ WORDSIZE ]   [ LSH ]     [ RSH ]     [ ROL ]     [ ROR ]     |");
        System.out.println("|  [   AND    ]   [ OR  ]     [ XOR ]     [ NOT ]     [ MOD ]     |");
        System.out.println("|  [    A     ]   [  B  ]     [  7  ]     [  8  ]     [  9  ] [*] |");
        System.out.println("|  [    C     ]   [  D  ]     [  4  ]     [  5  ]     [  6  ] [-] |");
        System.out.println("|  [    E     ]   [  F  ]     [  1  ]     [  2  ]     [  3  ] [+] |");
        System.out.println("|  [   +/-    ]   [  (  ]     [  )  ]     [  0  ]     [  =  ] [/] |");
        System.out.println("+-----------------------------------------------------------------+");
    }

    @Override
    public CalculationResult calculate(String operation, double... operands) {
        if (operation == null || operation.trim().isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        String op = operation.trim().toUpperCase();

        if (op.equals("WORDSIZE")) {
            WordSize[] all = WordSize.values();
            int next = (currentWordSize.ordinal() + 1) % all.length;
            this.currentWordSize = all[next];
            return new CalculationResult("WordSize", currentWordSize.getBits(), currentWordSize.getLabel());
        }

        long[] longOperands = new long[operands.length];
        for (int i = 0; i < operands.length; i++) {
            longOperands[i] = applyWordSize((long) operands[i]);
        }

        long resultValue;
        String expr;

        switch (op) {
            case "+":
            case "ADD":
                validateOperands(2, longOperands, "+");
                resultValue = applyWordSize(longOperands[0] + longOperands[1]);
                expr = longOperands[0] + " + " + longOperands[1];
                break;

            case "-":
            case "SUB":
                validateOperands(2, longOperands, "-");
                resultValue = applyWordSize(longOperands[0] - longOperands[1]);
                expr = longOperands[0] + " - " + longOperands[1];
                break;

            case "*":
            case "MUL":
                validateOperands(2, longOperands, "*");
                resultValue = applyWordSize(longOperands[0] * longOperands[1]);
                expr = longOperands[0] + " * " + longOperands[1];
                break;

            case "/":
            case "DIV":
                validateOperands(2, longOperands, "/");
                if (longOperands[1] == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                resultValue = applyWordSize(longOperands[0] / longOperands[1]);
                expr = longOperands[0] + " / " + longOperands[1];
                break;

            case "%":
            case "MOD":
                validateOperands(2, longOperands, "%");
                if (longOperands[1] == 0) {
                    throw new ArithmeticException("Cannot divide by zero for modulo");
                }
                resultValue = applyWordSize(longOperands[0] % longOperands[1]);
                expr = longOperands[0] + " % " + longOperands[1];
                break;

            case "AND":
            case "&":
                validateOperands(2, longOperands, "AND");
                resultValue = applyWordSize(longOperands[0] & longOperands[1]);
                expr = longOperands[0] + " AND " + longOperands[1];
                break;

            case "OR":
            case "|":
                validateOperands(2, longOperands, "OR");
                resultValue = applyWordSize(longOperands[0] | longOperands[1]);
                expr = longOperands[0] + " OR " + longOperands[1];
                break;

            case "XOR":
            case "^":
                validateOperands(2, longOperands, "XOR");
                resultValue = applyWordSize(longOperands[0] ^ longOperands[1]);
                expr = longOperands[0] + " XOR " + longOperands[1];
                break;

            case "NOT":
            case "~":
                validateOperands(1, longOperands, "NOT");
                resultValue = applyWordSize(~longOperands[0]);
                expr = "NOT(" + longOperands[0] + ")";
                break;

            case "LSH":
            case "<<":
                validateOperands(2, longOperands, "LSH");
                resultValue = applyWordSize(longOperands[0] << longOperands[1]);
                expr = longOperands[0] + " LSH " + longOperands[1];
                break;

            case "RSH":
            case ">>":
                validateOperands(2, longOperands, "RSH");
                resultValue = applyWordSize(longOperands[0] >> longOperands[1]);
                expr = longOperands[0] + " RSH " + longOperands[1];
                break;

            case "ROL":
                validateOperands(2, longOperands, "ROL");
                int shiftL = (int) (longOperands[1] % currentWordSize.getBits());
                long rolVal = (longOperands[0] << shiftL) | (longOperands[0] >>> (currentWordSize.getBits() - shiftL));
                resultValue = applyWordSize(rolVal);
                expr = longOperands[0] + " ROL " + longOperands[1];
                break;

            case "ROR":
                validateOperands(2, longOperands, "ROR");
                int shiftR = (int) (longOperands[1] % currentWordSize.getBits());
                long rorVal = (longOperands[0] >>> shiftR) | (longOperands[0] << (currentWordSize.getBits() - shiftR));
                resultValue = applyWordSize(rorVal);
                expr = longOperands[0] + " ROR " + longOperands[1];
                break;

            case "DISP":
            case "SHOW":
            case "RADIX":
                validateOperands(1, longOperands, "DISP");
                resultValue = longOperands[0];
                expr = "DISPLAY(" + longOperands[0] + ")";
                break;

            default:
                throw new UnsupportedOperationException(
                        "Operation '" + operation + "' is not supported in Programmer mode. Supported: " + getSupportedOperations()
                );
        }

        Map<String, String> multiBase = buildMultiBaseRepresentations(resultValue);
        return new CalculationResult(expr, (double) resultValue, String.valueOf(resultValue), multiBase);
    }

    private long applyWordSize(long val) {
        if (currentWordSize == WordSize.QWORD) {
            return val;
        }
        long mask = currentWordSize.getMask();
        long masked = val & mask;

        int bits = currentWordSize.getBits();
        long signBit = 1L << (bits - 1);
        if ((masked & signBit) != 0) {
            return masked | (~mask);
        }
        return masked;
    }

    public Map<String, String> buildMultiBaseRepresentations(long value) {
        Map<String, String> reps = new LinkedHashMap<>();
        int bits = currentWordSize.getBits();

        long unsignedVal = currentWordSize == WordSize.QWORD ? value : (value & currentWordSize.getMask());
        String hexStr = Long.toHexString(unsignedVal).toUpperCase();
        int hexDigits = bits / 4;
        hexStr = padLeft(hexStr, hexDigits, '0');
        reps.put("HEX", "0x" + hexStr);

        reps.put("DEC", String.valueOf(value));

        String octStr = Long.toOctalString(unsignedVal);
        reps.put("OCT", "0" + octStr);

        String binStr = Long.toBinaryString(unsignedVal);
        binStr = padLeft(binStr, bits, '0');
        reps.put("BIN", formatBinaryNibbles(binStr));

        return reps;
    }

    private String padLeft(String str, int length, char padChar) {
        if (str.length() >= length) {
            return str.substring(str.length() - length);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    private String formatBinaryNibbles(String binary) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binary.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                sb.append(" ");
            }
            sb.append(binary.charAt(i));
        }
        return sb.toString();
    }

    private void validateOperands(int expected, long[] operands, String opName) {
        if (operands == null || operands.length < expected) {
            throw new IllegalArgumentException("Operation '" + opName + "' requires " + expected + " operand(s)");
        }
    }

    @Override
    public void printHelp() {
        System.out.println("Programmer Calculator Usage:");
        System.out.println("  Bitwise Ops  : 'AND', 'OR', 'XOR', 'NOT' (e.g. calculate(\"AND\", 0b1100, 0b1010))");
        System.out.println("  Bit Shifts   : 'LSH', 'RSH', 'ROL', 'ROR' (e.g. calculate(\"LSH\", 5, 2))");
        System.out.println("  Arithmetic   : '+', '-', '*', '/', '%'");
        System.out.println("  Multi-Base   : 'DISP' (e.g. calculate(\"DISP\", 255) displays HEX, DEC, OCT, BIN)");
        System.out.println("  Word Sizes   : 'WORDSIZE' (cycles QWORD -> DWORD -> WORD -> BYTE)");
    }
}
