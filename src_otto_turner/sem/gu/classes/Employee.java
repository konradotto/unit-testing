package sem.gu.classes;

import java.util.Objects;
import java.util.UUID;

/**
 * Abstract Employee class provides the structure for all types of Employee within assignment 3 of DIT042.
 *
 * @author Konrad Otto
 * @version 1.0
 * @since 2018-10-06
 */
public class Employee {

    private final UUID id;
    private String name;
    private double grossSalary;
    private double netSalary;
    protected TaxingScheme taxingScheme;

    protected Employee(String name, double grossSalary) {
        id = UUID.randomUUID();
        this.name = name;
        this.grossSalary = grossSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s's gross salary is of %.2f SEK per month.");
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.netSalary = taxingScheme.calculateNetSalary(grossSalary);
        this.grossSalary = grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    //TODO this should never be used - should we remove it?
    // (Not sure whether Francisco wanted us to have this kind of thing)
    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public TaxingScheme getTaxingScheme() {
        return taxingScheme;
    }

    public void setTaxingScheme(TaxingScheme taxingScheme) {
        setNetSalary(grossSalary);
        this.taxingScheme = taxingScheme;
    }
}
