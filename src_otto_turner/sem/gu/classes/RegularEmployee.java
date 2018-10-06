package sem.gu.classes;

public class RegularEmployee extends Employee {

	public RegularEmployee(String name, double grossSalary) {
        super(name, grossSalary);
        //taxingScheme = StandarTax;      //TODO: implement this
    }
	
	//is this worth having as its own class?
	//surely there is no difference between this and the default employee class

    //TODO: further functionalities of StandardEmployee
}
