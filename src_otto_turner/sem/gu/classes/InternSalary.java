package sem.gu.classes;

public class InternSalary implements SalaryScheme {

    private static final int NO_PAY_BORDER = 5;
    private static final int BONUS_BORDER = 8;

    private static final double MIN_WAGE = 0.0;
    private static final double EXCELLENCE_BONUS = 1000.0;

    private GPA gpa;

    public InternSalary(GPA gpa) {
        setGpa(gpa);
    }

    @Override
    public double calculateGrossTotal(double basicGrossSalary) {
        double totalGross = basicGrossSalary;

        int gpa_int = gpa.getGpa();
        if (gpa_int <= NO_PAY_BORDER) {
            totalGross = MIN_WAGE;
        } else if (gpa_int >= BONUS_BORDER) {
            totalGross += EXCELLENCE_BONUS;
        }
        return totalGross;
    }

    @Override
    public double calculateNetSalary(double basicGrossSalary) {
        return calculateGrossTotal(basicGrossSalary);
    }

    public GPA getGpa() {
        return gpa;
    }

    public void setGpa(GPA gpa) {
        this.gpa = gpa;
    }
}
