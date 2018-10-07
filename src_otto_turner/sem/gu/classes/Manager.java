package sem.gu.classes;

public class Manager extends Employee{
	
	private DegreeType degree;

	public Manager(String name, double grossSalary, int id, DegreeType degree) {
		super(name,grossSalary,id);
		this.degree = degree;
	}
	
	public DegreeType getDegree() {
		return degree;
	}

	public void setDegree(DegreeType degree) {
		this.degree = degree;
	}
	
}
