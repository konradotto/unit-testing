package sem.gu.classes;

public class Intern extends Employee{
	
	private GPA gpa;
	
	public Intern(String name, double grossSalary, int id, GPA gpa) {
		super(name,grossSalary,id);
		this.gpa = gpa;
		setSalaryScheme(new InternSalary(gpa));
	}

	public GPA getGpa() {
		return gpa;
	}

	public void setGpa(GPA gpa) {
		this.gpa = gpa;
	}
	
}
