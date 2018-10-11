package sem.gu.classes;

import java.util.Objects;
import java.util.UUID;

/**
 * Abstract Employee class provides the structure for all types of Employee within assignment 3 of DIT042.
 *
 * @author Konrad Otto
 * @author Jack Turner
 * @version 1.0
 * @since 2018-10-06
 */
public class Employee {

    private int id; //id needs to be set by the user not the program
    private String name;
    private double grossSalary;
    private double netSalary;
    private TaxingScheme taxingScheme;

    public Employee(String name, double grossSalary, int id) {
        this.id = id;
        this.name = name;
        this.grossSalary = grossSalary;
        setTaxingScheme(new StandardTax());
    }

    public boolean equals(int id) {
        if(id == this.getId()) {
        	return true;
        } else {
        	return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%d's gross salary is of %.2f SEK per month.",this.id,this.grossSalary);
    }
    
    public boolean isDirector() {
    	if(this instanceof Director) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean isManager() {
    	if(this instanceof Manager) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean isIntern() {
    	if(this instanceof Intern) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public int getId() {
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
        setNetSalary(taxingScheme.calculateNetSalary(grossSalary));
        this.taxingScheme = taxingScheme;
    }
}
