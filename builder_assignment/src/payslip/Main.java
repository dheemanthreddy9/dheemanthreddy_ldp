package payslip;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. Comprehensive Payslip (All Allowances & Deductions) ===");
        Payslip full = new Payslip.PayslipBuilder("E101", "Ravi Kumar", "October 2026", 50000)
                .hra(20000)
                .da(5000)
                .conveyance(1600)
                .medical(1250)
                .specialAllowance(10000)
                .pf(6000)
                .professionalTax(200)
                .incomeTax(5000)
                .build();
        System.out.println(full);

        System.out.println("=== 2. Simple Payslip (Basic + Only PF Deduction) ===");
        Payslip simple = new Payslip.PayslipBuilder("E102", "Asha", "October 2026", 30000)
                .pf(3600)
                .build();
        System.out.println(simple);

        System.out.println("=== 3. Custom Payslip (Basic + HRA + Conveyance + PF + Prof. Tax) ===");
        Payslip custom = new Payslip.PayslipBuilder("E103", "Raj", "October 2026", 65000)
                .hra(26000)
                .conveyance(2000)
                .specialAllowance(15000)
                .pf(7800)
                .professionalTax(200)
                .incomeTax(8500)
                .build();
        System.out.println(custom);
    }
}
