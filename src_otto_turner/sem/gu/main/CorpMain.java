package sem.gu.main;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import sem.gu.classes.DegreeType;
import sem.gu.classes.Department;
import sem.gu.classes.Director;
import sem.gu.classes.Employee;
import sem.gu.classes.Intern;
import sem.gu.classes.Manager;
import sem.gu.classes.RegularEmployee;
import sem.gu.exceptions.NegativeInputException;

public class CorpMain {
	
	//GLOBAL VARIABLES
	private PrintStream ps;
	private Scanner scan;
	private Employee[] employees;
	private int numEmployees;
	private int dirBenefit;
	
	//CONSTANTS
	private final int NO_SELECTION = -1;
	private final int MAX_NUM_EMPLOYEES = 10;
	private final int DEFAULT_DIRECTOR_BENEFIT = 5000;
	
	//MAIN MENU CONSTANTS
	private final int REGISTER = 1;
	private final int REMOVE = 2;
	private final int RETRIEVE = 3;
	private final int DIR_BENEFIT = 4;
	private final int QUIT = 5;
	
	//REGISTER EMPLOYEE MENU CONSTANTS
	private final int EMPLOYEE = 1;
	private final int INTERN = 2;
	private final int MANAGER = 3;
	private final int DIRECTOR = 4;
	
	public CorpMain() {
		ps = new PrintStream(System.out);
		scan = new Scanner(System.in);
		dirBenefit = DEFAULT_DIRECTOR_BENEFIT;
		numEmployees = 0;
		this.employees = new Employee[MAX_NUM_EMPLOYEES];
		
	}
	
	//--- Input Primitives
	
	public double inputDouble(String message) {
    	double inputDouble = NO_SELECTION; //Set default values
    	while(inputDouble == NO_SELECTION) {
    		ps.println(message);
        	try {
        		inputDouble = scan.nextDouble(); //attempt to take in a double
        		if(inputDouble < 0) {
        			//entered value cannot be negative
        			throw new NegativeInputException();
        		}
        	} catch(InputMismatchException ex) {
                inputDouble = -1.0;
                ps.printf("InputMismatchException caught. Your input could not be parsed into a double.%n");
            } catch (NegativeInputException ex) {
        		inputDouble = -1.0;
        		ps.printf("%s%n", ex.getMessage());
        	} finally {
                scan.nextLine();
            }
    	}
    	return inputDouble;
    }
	
	public int inputInteger(String message) {
		int inputInt = NO_SELECTION; //Set default values
    	while(inputInt == NO_SELECTION) {
    		ps.println(message);
        	try {
        		inputInt = scan.nextInt(); //attempt to take in a double
        		if(inputInt < 0) {
        			//entered value cannot be negative
        			throw new NegativeInputException();
        		}
        	} catch(InputMismatchException ex) {
                inputInt = NO_SELECTION;
                ps.printf("InputMismatchException caught. Your input could not be parsed into an integer%n");
            } catch (NegativeInputException ex) {
                inputInt = NO_SELECTION;
                ps.printf("%s%n", ex.getMessage());
        	} finally {
                scan.nextLine();
            }
    	}
    	return inputInt;
	}
	
	public String inputString(String message) {
    	String inputString = ""; //Set default value
    	while(inputString.equals("")) {
    		ps.println(message); 
    		inputString = scan.nextLine();
    		if(inputString.equals("") || inputString.equals(" ")) {
    			//if entered string is empty of just a space, loop again
    			inputString = "";
    			ps.println("Entered value is invalid, value must have at least 1 character that is not a space");
    		}
    	}
    	return inputString;
    }

	//--- Main Menu
	
	public void run() {
		int option;
		printMenu();
		do {
			option = inputInteger(">>> TYPE OPTION NUMBER");
			
			switch(option) {
				case REGISTER:
					ps.printf(">>> OPTION %d SELECTED: REGISTERING AN EMPLOYEE%n",REGISTER);
					ps.println(">>>");
					if(numEmployees >= MAX_NUM_EMPLOYEES) {
						ps.println(">>> ERROR: MAX NUMBER OF EMPLOYEES REACHED. REMOVE EMPLOYEES BEFORE ADDING MORE");
					} else {
						Employee employee = registerEmployee();
						numEmployees += 1;
						employees[numEmployees] = employee;
						ps.printf(">>> EMPLOYEE REGISTERED. THERE IS NOW %d EMPLOYEE(S) REGISTERED%n",numEmployees);
					}
					break;
				case REMOVE:
					ps.printf(">>> OPTION %d SELECTED: REMOVING AN EMPLOYEE%n",REMOVE);
					ps.println(">>>");
					break;
				case RETRIEVE:
					ps.printf(">>> OPTION %d SELECTED: RETRIEVING AN EMPLOYEE%n",RETRIEVE);
					ps.println(">>>");
					break;
				case DIR_BENEFIT:
					ps.printf(">>> OPTION %D SELECTED: SET DIRECTOR BENEFITS%n",DIR_BENEFIT);
					ps.println(">>>");
				case QUIT:
					ps.printf(">>> OPTION %d SELECTED: EXITING SYSTEM%n",QUIT);
					ps.println(">>> HAVE A NICE DAY...");
					break;
				default:
					ps.println(">>> ERROR: INVALID OPTION SELECTED");
					break;
			}
		} while (option != QUIT);
	}
	
	public void printMenu() {
		ps.println("==<*>== WELCOME TO REUSAX CORP EMPLOYEE SYSTEM ==<*>==");
		ps.println(">>> CHOOSE AN OPTION BELOW");
		ps.println(">>>");
		ps.println(">>> 1. REGISTER AN EMPLOYEE");
		ps.println(">>> 2. REMOVE AN EMPLOYEE");
		ps.println(">>> 3. RETRIEVE AN EMPLOYEE DETAILS");
		ps.println(">>> 4. SET DIRECTOR BENEFIT");
		ps.println(">>> 5. QUIT");
		ps.println(">>>");
	}
	
	//---Create Employee
	
	public Employee registerEmployee() {
		Employee employee = null;
		int option = NO_SELECTION;
		do {
			String name = inputString(">>> PLEASE ENTER EMPLOYEES NAME");
			double grossSalary = inputDouble(">>> PLEASE ENTER EMPLOYEES GROSS SALARY");
			printEmployeeRegisterMenu();
			option = inputInteger(">>> TYPE OPTION CHOICE");
			switch(option) {
				case EMPLOYEE:
					ps.println(">>> ATTEMPTING TO CREATE EMPLOYEE...");
					employee = new RegularEmployee(name,grossSalary);
					ps.println(">>> EMPLOYEE CREATION SUCCESSFULL");
					break;
				case INTERN:
					ps.println(">>> ATTEMPTING TO CREATE INTERN...");
					employee = createIntern(name,grossSalary);
					ps.println(">>> INTERN CREATION SUCCESSFULL");
					break;
				case MANAGER:
					ps.println(">>> ATTEMPTING TO CREATE MANAGER...");
					employee = createManager(name,grossSalary);
					ps.println(">>> MANAGER CREATION SUCCESSFULL");
					break;
				case DIRECTOR:
					ps.println(">>> ATTEMPTING TO CREATE DIRECTOR...");
					employee = createDirector(name,grossSalary);
					ps.println(">>> DIRECTOR CREATION SUCCESSFULL");
					break;
				default:
					ps.println(">>> ERROR: INVALID OPTION SELECTED");
					option = NO_SELECTION;
					break;
			}
		} while (option == NO_SELECTION);
		return employee;
	}
	
	public void printEmployeeRegisterMenu() {
		ps.println(">>> CHOOSE WHAT TYPE OF EMPLOYEE YOU WISH TO ADD");
		ps.println(">>> 1. REGULAR EMPLOYEE");
		ps.println(">>> 2. INTERN");
		ps.println(">>> 3. MANAGER");
		ps.println(">>> 4. DIRECTOR");
	}
	
	public Intern createIntern(String name, double grossSalary) {
		int gpa = NO_SELECTION;
		do {
			gpa = inputInteger(">>> PLEASE ENTER THE GPA (0-10) OF THE INTERN");
			if(gpa < 0 || gpa > 10) gpa = NO_SELECTION; 
		} while (gpa == NO_SELECTION);
		Intern intern = new Intern(name, grossSalary, gpa);
		return intern;
	}
	
	public Manager createManager(String name, double grossSalary) {
		DegreeType degree = retrieveDegreeType();
		Manager manager = new Manager(name, grossSalary,degree);
		return manager;
	}
	
	public Director createDirector(String name, double grossSalary) {
		DegreeType degree = retrieveDegreeType();
		Department department = retrieveDepartment();
		Director director = new Director(name,grossSalary,degree,department,dirBenefit);
		return director;
	}
	
	public DegreeType retrieveDegreeType() {
		DegreeType degree = DegreeType.NA;
		do {
			String dt = inputString(">>> ENTER THE DEGREE CLASSIFICATION OF THE EMPLOYEE");
			if(dt.equalsIgnoreCase("BSc")) {
				degree = DegreeType.BSc;
			} else if(dt.equalsIgnoreCase("MSc")) {
				degree = DegreeType.MSc;
			} else if(dt.equalsIgnoreCase("PhD")) {
				degree = DegreeType.PhD;
			}
		} while (degree == DegreeType.NA);
		return degree;
	}
	
	public Department retrieveDepartment() {
		Department department = Department.NA;
		do {
			String dt = inputString(">>> ENTER THE DEPARTMENT OF THE EMPLOYEE (HR/TECH/BUSI)");
			if(dt.equalsIgnoreCase("HR")) {
				department = Department.HR;
			} else if(dt.equalsIgnoreCase("TECH")) {
				department = Department.TECHNICAL;
			} else if(dt.equalsIgnoreCase("BUSI")) {
				department = Department.BUSINESS;
			}
		} while (department == Department.NA);
		return department;
	}
	
	//---Remove Employee
	
	public void removeEmployee() {
		
	}
	
	public static void main(String[] args) {
		CorpMain program = new CorpMain();
		program.run();
	}
	
}
