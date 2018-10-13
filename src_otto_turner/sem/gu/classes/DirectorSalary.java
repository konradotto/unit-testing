package sem.gu.classes;

import sem.gu.enums.DegreeType;

public class DirectorSalary extends ManagerSalary {

    private static final double LOW_TAX_BORDER = 30000;
    private static final double MEDIUM_TAX_BORDER = 50000;

    private static final double LOW_TAX = StandardSalary.STANDARD_TAX_RATE;
    private static final double MEDIUM_TAX = 0.2;
    private static final double HIGH_TAX = 0.4;

    public DirectorSalary(DegreeType degreeType) {
        super(degreeType);
    }

    @Override
    public double calculateGrossTotal(double basicGrossSalary) {
        double bonus = super.determineBonus(basicGrossSalary);
        return basicGrossSalary * (1.0 + bonus) + Director.getBenefit();
    }

    @Override
    /**
     * Function calculating the net salary given the basic gross salary.
     * Evaluates the total gross salary first.
     * @param   basicGrossSalary    gross salary without boni etc. of the desired manager
     * @return net salary of an employee
     */
    public double calculateNetSalary(double basicGrossSalary) {
        double grossTotal = calculateGrossTotal(basicGrossSalary);

        // Francisco is gonna hate this :D
        double baseTax = (grossTotal <= LOW_TAX_BORDER) ? LOW_TAX : MEDIUM_TAX;

        double netSalary;
        if (grossTotal <= MEDIUM_TAX_BORDER) {
            netSalary = grossTotal - (baseTax * grossTotal);
        } else {
            double maxTaxed = grossTotal - LOW_TAX_BORDER;
            netSalary = grossTotal - (LOW_TAX_BORDER * MEDIUM_TAX + maxTaxed * HIGH_TAX);
        }
        return netSalary;
    }
}
