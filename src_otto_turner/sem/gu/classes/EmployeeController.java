package sem.gu.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class EmployeeController {

    public final static String COMPARE_NAME = "By Name";
    public final static String COMPARE_NET = "By Net Salary";
    public final static String ASCENDING = "ASCENDING";
    public final static String DESCENDING = "DESCENDING";

    private static EmployeeComparatorByName byName;
    private static EmployeeComparatorByNet byNet;

    private ArrayList<Employee> employees;
    IOManager ioManager;

    public EmployeeController(IOManager ioManager) {
        this.employees = new ArrayList<Employee>();
        this.ioManager = ioManager;

        byName = new EmployeeComparatorByName();
        byNet = new EmployeeComparatorByNet();
    }

    public Employee getEmployee(int index) {
        try {
            return employees.get(index);
        } catch (IndexOutOfBoundsException e) {
            ioManager.println("ERROR: Index out of bounds. Employee not listed. Can't be retrieved.");
            return null;
        }
    }

    public int getSize() {
        return employees.size();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(int index) {
        try {
            employees.remove(index);
        } catch (IndexOutOfBoundsException e) {
            ioManager.println("ERROR: Index out of bounds. Employee not listed. Can't be removed.");
        }
    }

    public void removeEmployee(Employee employee) {
        try {
            employees.remove(employee);
        } catch (Exception e) {
            ioManager.println("ERROR: Employee not in the list.");
        }
    }

    public void sortBy(String a, String b) {
        Comparator<Employee> c = byName;    // DEFAULT

        if (a.equalsIgnoreCase(COMPARE_NET)) {
            c = byNet;
        }

        if (b != null && b.equalsIgnoreCase(DESCENDING)) {
            c = c.reversed();
        }

        employees.sort(c);
    }

    public void printAll() {
        for (Employee emp : employees) {
            ioManager.println(emp);
        }
    }
}
