package sem.gu.classes;

import java.util.Comparator;

public class EmployeeComparatorByNet implements Comparator<Employee> {

    @Override
    public int compare(Employee employee, Employee t1) {
        Double empSalary = Double.valueOf(employee.getNetSalary());
        Double t1Salary = Double.valueOf(t1.getNetSalary());
        return empSalary.compareTo(t1Salary);
    }
}
