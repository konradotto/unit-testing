package sem.gu.classes;

import sem.gu.enums.DegreeType;

public class ManagerSalary implements SalaryScheme {

    private DegreeType degreeType;

    public ManagerSalary(DegreeType degree) {
        degreeType = degree;
    }

    protected double calculateBonus(double subtotal) {
        double bonus;
        switch (degreeType) {
            case BSc:
                bonus = Employee.BSC_BONUS;
                break;
            case MSc:
                bonus = Employee.MSC_BONUS;
                break;
            case PhD:
                bonus = Employee.PHD_BONUS;
                break;
            default:
                bonus = Employee.DEFAULT_BONUS;
        }
        return bonus;
    }

    @Override
    public double calculateGrossTotal(double basicGrossSalary) {
        return basicGrossSalary * (1.0 + calculateBonus(basicGrossSalary));
    }

    @Override
    public double calculateNetSalary(double basicGrossSalary) {
        double grossTotal = calculateGrossTotal(basicGrossSalary);

        return grossTotal - (StandardSalary.STANDARD_TAX_RATE * grossTotal);
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }
}
