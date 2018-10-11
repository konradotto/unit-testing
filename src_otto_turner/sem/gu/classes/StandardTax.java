package sem.gu.classes;

public static class StandardTax implements TaxingScheme {

    private static final double STANDARD_TAX_RATE = 0.1;

    @Override
    public double calculateNetSalary(double grossSalary) {
        return grossSalary - (STANDARD_TAX_RATE * grossSalary);
    }
}
