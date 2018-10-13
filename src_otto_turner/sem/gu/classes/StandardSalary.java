package sem.gu.classes;

public class StandardSalary implements SalaryScheme {

    private static final double STANDARD_TAX_RATE = 0.1;

    @Override
    public double calculateGrossTotal(double basicGrossSalary) {
        return basicGrossSalary;
    }

    @Override
    public double calculateNetSalary(double basicGrossSalary) {
        return basicGrossSalary - (STANDARD_TAX_RATE * basicGrossSalary);
    }
}
