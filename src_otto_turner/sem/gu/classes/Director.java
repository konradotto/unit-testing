package sem.gu.classes;

public class Director extends Manager{
	
	private Department department;
	private double benefit;
	
	public Director(String name, double grossSalary,DegreeType degree, Department department, double benefit) {
		super(name, grossSalary,degree);
		this.department = department;
		this.benefit = benefit;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public double getBenefit() {
		return benefit;
	}

	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}
	
}
