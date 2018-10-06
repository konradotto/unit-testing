package sem.gu.classes;

public class RegularEmployee extends Employee {

    Employee(String name, double grossSalary) {
        super(name, grossSalary);
        taxingScheme = StandarTax;      //TODO: implement this
    }

    //TODO: further functionalities of StandardEmployee
}
