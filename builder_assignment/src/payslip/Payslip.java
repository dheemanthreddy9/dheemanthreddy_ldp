package payslip;

public class Payslip {

    private final String employeeId;
    private final String employeeName;
    private final String month;

    private final double basic;
    private final double hra;
    private final double da;
    private final double conveyance;
    private final double medical;
    private final double specialAllowance;

    private final double pf;
    private final double professionalTax;
    private final double incomeTax;

    private final double grossSalary;
    private final double totalDeductions;
    private final double netPay;

    private Payslip(PayslipBuilder b) {
        this.employeeId = b.employeeId;
        this.employeeName = b.employeeName;
        this.month = b.month;
        this.basic = b.basic;
        this.hra = b.hra;
        this.da = b.da;
        this.conveyance = b.conveyance;
        this.medical = b.medical;
        this.specialAllowance = b.specialAllowance;
        this.pf = b.pf;
        this.professionalTax = b.professionalTax;
        this.incomeTax = b.incomeTax;

        this.grossSalary = basic + hra + da + conveyance + medical + specialAllowance;
        this.totalDeductions = pf + professionalTax + incomeTax;
        this.netPay = grossSalary - totalDeductions;
    }

    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getMonth() { return month; }
    public double getBasic() { return basic; }
    public double getHra() { return hra; }
    public double getDa() { return da; }
    public double getConveyance() { return conveyance; }
    public double getMedical() { return medical; }
    public double getSpecialAllowance() { return specialAllowance; }
    public double getPf() { return pf; }
    public double getProfessionalTax() { return professionalTax; }
    public double getIncomeTax() { return incomeTax; }

    public double getGrossSalary() { return grossSalary; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getNetPay() { return netPay; }

    @Override
    public String toString() {
        return "===== PAYSLIP - " + month + " =====\n"
                + "Employee      : " + employeeName + " (" + employeeId + ")\n"
                + "--- Earnings ---\n"
                + "Basic         : " + basic + "\n"
                + "HRA           : " + hra + "\n"
                + "DA            : " + da + "\n"
                + "Conveyance    : " + conveyance + "\n"
                + "Medical       : " + medical + "\n"
                + "Special Allow.: " + specialAllowance + "\n"
                + "Gross Salary  : " + grossSalary + "\n"
                + "--- Deductions ---\n"
                + "PF            : " + pf + "\n"
                + "Prof. Tax     : " + professionalTax + "\n"
                + "Income Tax    : " + incomeTax + "\n"
                + "Total Deduct. : " + totalDeductions + "\n"
                + "NET PAY       : " + netPay + "\n";
    }

    public static class PayslipBuilder {

        private final String employeeId;
        private final String employeeName;
        private final String month;
        private final double basic;

        private double hra;
        private double da;
        private double conveyance;
        private double medical;
        private double specialAllowance;
        private double pf;
        private double professionalTax;
        private double incomeTax;

        public PayslipBuilder(String employeeId, String employeeName, String month, double basic) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.month = month;
            this.basic = basic;
        }

        public PayslipBuilder hra(double hra) { this.hra = hra; return this; }
        public PayslipBuilder da(double da) { this.da = da; return this; }
        public PayslipBuilder conveyance(double conveyance) { this.conveyance = conveyance; return this; }
        public PayslipBuilder medical(double medical) { this.medical = medical; return this; }
        public PayslipBuilder specialAllowance(double specialAllowance) { this.specialAllowance = specialAllowance; return this; }
        public PayslipBuilder pf(double pf) { this.pf = pf; return this; }
        public PayslipBuilder professionalTax(double professionalTax) { this.professionalTax = professionalTax; return this; }
        public PayslipBuilder incomeTax(double incomeTax) { this.incomeTax = incomeTax; return this; }

        public Payslip build() {
            if (employeeId == null || employeeId.trim().isEmpty()) {
                throw new IllegalStateException("Employee ID cannot be empty");
            }
            if (employeeName == null || employeeName.trim().isEmpty()) {
                throw new IllegalStateException("Employee Name cannot be empty");
            }
            if (basic <= 0) {
                throw new IllegalStateException("Basic salary must be greater than 0");
            }
            return new Payslip(this);
        }
    }
}
