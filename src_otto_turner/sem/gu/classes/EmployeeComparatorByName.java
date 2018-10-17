package sem.gu.classes;

import java.util.Comparator;

public class EmployeeComparatorByName implements Comparator<Employee> {

    @Override
    public int compare(Employee employee, Employee t1) {
        int comparison =  employee.getName().compareTo(t1.getName());

        // sort by basic gross salary when equal name
        if (comparison == 0) {
            Double gross = Double.valueOf(employee.getBasicGrossSalary());
            Double t1Gross = Double.valueOf(t1.getBasicGrossSalary());
            comparison = gross.compareTo(t1Gross);
        }

        return comparison;
    }
}
