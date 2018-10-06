package sem.gu.classes;

public class Intern extends Employee{
	
	private int gpa;
	
	public Intern(String name, double grossSalary, int gpa) {
		super(name,grossSalary);
		this.gpa = gpa;
	}

	public int getGpa() {
		return gpa;
	}

	public void setGpa(int gpa) {
		this.gpa = gpa;
	}
	
}
