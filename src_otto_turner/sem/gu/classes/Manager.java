package sem.gu.classes;

import sem.gu.enums.DegreeType;

public class Manager extends Employee {

    private DegreeType degree;
    private double grossSalaryWithBonus;

    public Manager(String name, double grossSalary, int id, DegreeType degree) {
        super(name, grossSalary, id);

        this.degree = degree;
        this.setSalaryWithBonus();
    }

    private void setSalaryWithBonus() {
        double bonus;
        switch (degree) {
            case BSc:
                bonus = BSC_BONUS;
                break;
            case MSc:
                bonus = MSC_BONUS;
                break;
            case PhD:
                bonus = PHD_BONUS;
                break;
            default:
                bonus = DEFAULT_BONUS;
        }
        this.grossSalaryWithBonus = getGrossSalary() * (1.0 + bonus);

        // set salary after taxes according to bonus
        super.setNetSalary(grossSalaryWithBonus);
    }

    public DegreeType getDegree() {
        return degree;
    }

    public void setDegree(DegreeType degree) {
        this.degree = degree;
        setSalaryWithBonus();
    }

}
