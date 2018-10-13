package sem.gu.classes;

import java.util.Objects;

/**
 * Abstract Employee class provides the structure for all types of Employee within assignment 3 of DIT042.
 *
 * @author Konrad Otto
 * @author Jack Turner
 * @version 1.0
 * @since 2018-10-06
 */
public class Employee {

    // Constants
    final static double DEFAULT_BONUS = 0.00;
    final static double BSC_BONUS = 0.10;
    final static double MSC_BONUS = 0.20;
    final static double PHD_BONUS = 0.35;

    private int id; //id needs to be set by the user not the program
    private String name;
    private double basicGrossSalary;
    private SalaryScheme salaryScheme;

    public Employee(String name, double grossSalary, int id) {
        this.id = id;
        this.name = name;
        this.basicGrossSalary = grossSalary;
        setSalaryScheme(new StandardSalary());
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
        return String.format("%d's gross salary is of %.2f SEK per month.",this.id,this.basicGrossSalary);
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

    public double getBasicGrossSalary() {
        return basicGrossSalary;
    }

    public void setBasicGrossSalary(double basicGrossSalary) {
        this.basicGrossSalary = basicGrossSalary;
    }

    public double getNetSalary() {
        return salaryScheme.calculateNetSalary(basicGrossSalary);
    }

    public SalaryScheme getSalaryScheme() {
        return salaryScheme;
    }

    public void setSalaryScheme(SalaryScheme salaryScheme) {
        this.salaryScheme = salaryScheme;
    }
}
