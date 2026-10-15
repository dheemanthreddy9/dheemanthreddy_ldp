class PrinterManager {
    private static PrinterManager instance;

    private PrinterManager() {
        System.out.println("Printer Manager Created");
    }

    public static PrinterManager getInstance() {
        if (instance == null) {
            instance = new PrinterManager();
        }
        return instance;
    }

    public void printDocument(String document) {
        System.out.println("Printing: " + document);
    }
}

public class Main {
    public static void main(String[] args) {
        PrinterManager printer1 = PrinterManager.getInstance();
        PrinterManager printer2 = PrinterManager.getInstance();

        printer1.printDocument("Report.pdf");
        printer2.printDocument("Invoice.pdf");

        System.out.println(printer1 == printer2);
    }
}
