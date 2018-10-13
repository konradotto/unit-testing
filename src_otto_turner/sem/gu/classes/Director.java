package sem.gu.classes;

import sem.gu.enums.DegreeType;
import sem.gu.enums.Department;

public class Director extends Manager {

    private Department department;
    private static double benefit = 0.0;

    public Director(String name, double grossSalary, int id, DegreeType degree, Department department) {
        super(name, grossSalary, id, degree);
        this.department = department;
        setSalaryScheme(new DirectorSalary(this.getDegree()));
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public static double getBenefit() {
        return benefit;
    }

    public static void setBenefit(double globalBenefit) {
        benefit = globalBenefit;
    }

}
