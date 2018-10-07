package sem.gu.classes;

public class Intern extends Employee{
	
	private int gpa;
	
	public Intern(String name, double grossSalary, int id, int gpa) {
		super(name,grossSalary,id);
		this.gpa = gpa;
	}

	public int getGpa() {
		return gpa;
	}

	public void setGpa(int gpa) {
		this.gpa = gpa;
	}
	
}
