package sem.gu.classes;

import sem.gu.enums.DegreeType;

public class ManagerSalary implements SalaryScheme {

    private DegreeType degreeType;

    public ManagerSalary(DegreeType degree) {
        degreeType = degree;
    }

    @Override
    public double calculateGrossTotal(double subtotal) {
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
        return subtotal * (1.0 + bonus);
    }

    @Override
    public double calculateNetSalary(double grossSalary) {
        double grossTotal = calculateGrossTotal(grossSalary);

        return grossTotal - (StandardSalary.STANDARD_TAX_RATE * grossTotal);
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }
}
