package sem.gu.main;

import java.io.PrintStream;
import java.util.ArrayList;
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

	// GLOBAL VARIABLES
	private PrintStream ps;
	private Scanner scan;
	private ArrayList<Employee> employees;
	private int numEmployees;
	private int dirBenefit;

	// CONSTANTS
	private final int NO_SELECTION = -1;
	private final int DEFAULT_DIRECTOR_BENEFIT = 5000;

	// MAIN MENU CONSTANTS
	private final int REGISTER = 1;
	private final int REMOVE = 2;
	private final int RETRIEVE = 3;
	private final int UPDATE_INFO = 4;
	private final int DIR_BENEFIT = 5;
	private final int QUIT = 6;

	// REGISTER EMPLOYEE MENU CONSTANTS
	private final int EMPLOYEE = 1;
	private final int INTERN = 2;
	private final int MANAGER = 3;
	private final int DIRECTOR = 4;

	// UPDATE EMPLOYEE MENU CONSTANTS
	private final int NAME = 1;
	private final int SALARY = 2;

	public CorpMain() {
		this.ps = new PrintStream(System.out);
		this.scan = new Scanner(System.in);
		this.dirBenefit = DEFAULT_DIRECTOR_BENEFIT;
		this.numEmployees = 0;
		this.employees = new ArrayList<Employee>();

	}

	// --- Input Primitives

	public double inputDouble(String message) {
		double inputDouble = NO_SELECTION; // Set default values
		while (inputDouble == NO_SELECTION) {
			ps.println(message);
			try {
				inputDouble = scan.nextDouble(); // attempt to take in a double
				if (inputDouble < 0) {
					// entered value cannot be negative
					throw new NegativeInputException();
				}
			} catch (InputMismatchException ex) {
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
		int inputInt = NO_SELECTION; // Set default values
		while (inputInt == NO_SELECTION) {
			ps.println(message);
			try {
				inputInt = scan.nextInt(); // attempt to take in a double
				if (inputInt < 0) {
					// entered value cannot be negative
					throw new NegativeInputException();
				}
			} catch (InputMismatchException ex) {
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
		String inputString = ""; // Set default value
		while (inputString.equals("")) {
			ps.println(message);
			inputString = scan.nextLine();
			if (inputString.equals("") || inputString.equals(" ")) {
				// if entered string is empty of just a space, loop again
				inputString = "";
				ps.println("Entered value is invalid, value must have at least 1 character that is not a space");
			}
		}
		return inputString;
	}

	// --- Retrieve Employee

	public Employee retrieveEmployee(int id) {
		for (Employee em : employees) {
			if (em.equals(id)) {
				ps.printf(">>> EMPLOYEE %d FOUND%n", id);
				return em;
			}
		}
		ps.printf(">>>ERROR: AN EMPLOYEE OF ID %d IS NOT REGISTERED IN THE SYSTEM%n", id);
		return null;
	}

	// --- Check Existing

	public boolean checkEmployeeExists(int id) {
		for (Employee em : employees) {
			if (em.equals(id)) {
				return true;
			}
		}
		return false;
	}

	// --- Main Menu

	public void run() {
		ps.println("==<*>== WELCOME TO REUSAX CORP EMPLOYEE SYSTEM ==<*>==");
		int option;
		do {
			printMenu();
			option = inputInteger(">>> TYPE OPTION NUMBER");
			switch (option) {
			case REGISTER:
				ps.printf(">>> OPTION %d SELECTED: REGISTERING AN EMPLOYEE%n", REGISTER);
				ps.println(">>>");
				Employee employee = registerEmployee();
				numEmployees += 1;
				employees.add(employee);
				ps.printf(">>> EMPLOYEE REGISTERED. THERE IS NOW %d EMPLOYEE(S) REGISTERED%n", numEmployees);
				break;
			case REMOVE:
				ps.printf(">>> OPTION %d SELECTED: REMOVING AN EMPLOYEE%n", REMOVE);
				ps.println(">>>");
				removeEmployee();
				break;
			case RETRIEVE:
				ps.printf(">>> OPTION %d SELECTED: PRINTING EMPLOYEE DETAILS%n", RETRIEVE);
				ps.println(">>>");
				int id = inputInteger(">>> ENTER THE ID OF THE EMPLOYEE");
				Employee em = retrieveEmployee(id);
				if (em != null) {
					ps.println(em.toString());
				}
				break;
			case UPDATE_INFO:
				ps.printf(">>> OPTION %d SELECTED: UPDATE EMPLOYEE INFO", UPDATE_INFO);
				ps.println(">>>");
				updateInfo();
				break;
			case DIR_BENEFIT:
				ps.printf(">>> OPTION %d SELECTED: SET DIRECTOR BENEFITS%n", DIR_BENEFIT);
				ps.println(">>>");
				setDirectorBenefits();
				break;
			case QUIT:
				ps.printf(">>> OPTION %d SELECTED: EXITING SYSTEM%n", QUIT);
				ps.println(">>> HAVE A NICE DAY...");
				break;
			default:
				ps.println(">>> ERROR: INVALID OPTION SELECTED");
				break;
			}
		} while (option != QUIT);
	}

	public void printMenu() {
		ps.println(">>>");
		ps.println(">>> CHOOSE AN OPTION BELOW");
		ps.println(">>>");
		ps.println(">>> 1. REGISTER AN EMPLOYEE");
		ps.println(">>> 2. REMOVE AN EMPLOYEE");
		ps.println(">>> 3. PRINT AN EMPLOYEE'S DETAILS");
		ps.println(">>> 4. UPDATE EMPLOYEE DETAILS");
		ps.println(">>> 5. SET DIRECTOR BENEFIT");
		ps.println(">>> 6. QUIT");
		ps.println(">>>");
	}

	// --- Create Employee

	public Employee registerEmployee() {
		Employee employee = null;
		int option = NO_SELECTION;
		int id = inputInteger(">>> ENTER EMPLOYEES IDENTIFICATION NUMBER");
		if (checkEmployeeExists(id)) {
			ps.println(">>> THIS EMPLOYEE IS ALREADY REGISTERED, CANCELLING REGISTRATION");
		} else {
			do {
				String name = inputString(">>> PLEASE ENTER EMPLOYEES NAME");
				double grossSalary = inputDouble(">>> PLEASE ENTER EMPLOYEES GROSS SALARY");
				printEmployeeRegisterMenu();
				option = inputInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case EMPLOYEE:
					ps.println(">>> ATTEMPTING TO CREATE EMPLOYEE...");
					employee = new RegularEmployee(name, grossSalary, id);
					ps.println(">>> EMPLOYEE CREATION SUCCESSFULL");
					break;
				case INTERN:
					ps.println(">>> ATTEMPTING TO CREATE INTERN...");
					employee = createIntern(name, grossSalary, id);
					ps.println(">>> INTERN CREATION SUCCESSFULL");
					break;
				case MANAGER:
					ps.println(">>> ATTEMPTING TO CREATE MANAGER...");
					employee = createManager(name, grossSalary, id);
					ps.println(">>> MANAGER CREATION SUCCESSFULL");
					break;
				case DIRECTOR:
					ps.println(">>> ATTEMPTING TO CREATE DIRECTOR...");
					employee = createDirector(name, grossSalary, id);
					ps.println(">>> DIRECTOR CREATION SUCCESSFULL");
					break;
				default:
					ps.println(">>> ERROR: INVALID OPTION SELECTED");
					option = NO_SELECTION;
					break;
				}
			} while (option == NO_SELECTION);
		}
		return employee;
	}

	public void printEmployeeRegisterMenu() {
		ps.println(">>> CHOOSE WHAT TYPE OF EMPLOYEE YOU WISH TO ADD");
		ps.println(">>> 1. REGULAR EMPLOYEE");
		ps.println(">>> 2. INTERN");
		ps.println(">>> 3. MANAGER");
		ps.println(">>> 4. DIRECTOR");
	}

	public Intern createIntern(String name, double grossSalary, int id) {
		int gpa = NO_SELECTION;
		do {
			gpa = inputInteger(">>> PLEASE ENTER THE GPA (0-10) OF THE INTERN");
			if (gpa < 0 || gpa > 10)
				gpa = NO_SELECTION;
		} while (gpa == NO_SELECTION);
		Intern intern = new Intern(name, grossSalary, id, gpa);
		return intern;
	}

	public Manager createManager(String name, double grossSalary, int id) {
		DegreeType degree = retrieveDegreeType();
		Manager manager = new Manager(name, grossSalary, id, degree);
		return manager;
	}

	public Director createDirector(String name, double grossSalary, int id) {
		DegreeType degree = retrieveDegreeType();
		Department department = retrieveDepartment();
		Director director = new Director(name, grossSalary, id, degree, department, dirBenefit);
		return director;
	}

	public DegreeType retrieveDegreeType() {
		DegreeType degree = DegreeType.NA;
		do {
			String dt = inputString(">>> ENTER THE DEGREE CLASSIFICATION OF THE EMPLOYEE (BSC/MSC/PHD)");
			if (dt.equalsIgnoreCase("BSc")) {
				degree = DegreeType.BSc;
			} else if (dt.equalsIgnoreCase("MSc")) {
				degree = DegreeType.MSc;
			} else if (dt.equalsIgnoreCase("PhD")) {
				degree = DegreeType.PhD;
			}
		} while (degree == DegreeType.NA);
		return degree;
	}

	public Department retrieveDepartment() {
		Department department = Department.NA;
		do {
			String dt = inputString(">>> ENTER THE DEPARTMENT OF THE EMPLOYEE (HR/TECH/BUSI)");
			if (dt.equalsIgnoreCase("HR")) {
				department = Department.HR;
			} else if (dt.equalsIgnoreCase("TECH")) {
				department = Department.TECHNICAL;
			} else if (dt.equalsIgnoreCase("BUSI")) {
				department = Department.BUSINESS;
			}
		} while (department == Department.NA);
		return department;
	}

	// --- Remove Employee

	public void removeEmployee() {
		int id = inputInteger(">>> ENTER THE ID OF THE EMPLOYEE TO DELETE");
		int index = NO_SELECTION;

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).equals(id))
				;
			ps.println(">>> EMPLOYEE FOUND. PREPARING DELETION");
			index = i;
		}

		if (index == NO_SELECTION) {
			ps.println(">>> ERROR: EMPLOYEE NOT FOUND. DELETION CANCELLED");
		} else {
			employees.remove(index);
			numEmployees--;
			// TODO clean up arraylist
			ps.println(">>> EMPLOYEE DELETED");
		}
	}

	// --- Set Director Benefits

	public void setDirectorBenefits() {
		double benefit = inputDouble(">>> ENTER NEW BENEFIT AMOUNT");
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).isDirector()) {
				Director dir = (Director) employees.get(i);
				dir.setBenefit(benefit);
				ps.printf(">>> DIRECTOR %d NEW BENEFIT SET%n", dir.getId());
			}
		}
	}

	// --- Update Employee Information

	public void updateInfo() {
		int id = inputInteger(">>> PLEASE ENTER THE ID OF THE EMPLOYEE YOU WANT TO UPDATE");
		Employee em = retrieveEmployee(id);
		if (em != null) {
			printUpdateMenu();
			int option = NO_SELECTION;
			do {
				option = inputInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case NAME:
					ps.println(">>> UPDATING EMPLOYEE NAME...");
					String name = inputString(">>> ENTER NEW NAME");
					em.setName(name);
					ps.println(">>> NAME UPDATED");
					break;
				case SALARY:
					ps.println(">>> UPDATING EMPLOYEE SALARY...");
					double salary = inputDouble(">>> ENTER NEW SALARY");
					em.setGrossSalary(salary);
					ps.println(">>> SALARY UPDATED");
					break;
				}
			} while (option == NO_SELECTION);
		}
	}

	public void printUpdateMenu() {
		ps.println(">>> PLEASE SELECT AN OPTION BELOW");
		ps.println(">>>");
		ps.println(">>> 1. UPDATE NAME");
		ps.println(">>> 2. UPDATE GROSS SALARY");
	}

	// --- Promotions

	public void promote() {
		int id = inputInteger(">>> ENTER EMPLOYEE ID NUMBER");
		Employee em = retrieveEmployee(id);
		if (em != null) {
			printPromotionMenu();
			int option = NO_SELECTION;
			do {
				option = inputInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case EMPLOYEE:
					promoteToRegularEmployee(em);
					break;
				case INTERN:
					int gpa = inputInteger(">>> ENTER GPA (0-10)");
					promoteToIntern(em, gpa);
					break;
				case MANAGER:
					DegreeType degree = DegreeType.NA;
					if(em.isDirector()) {
						//degree = em.getDegree();
					} else {
						degree = retrieveDegreeType();
					}
					
					promoteToManager(em, degree);
					break;
				case DIRECTOR:
					
					break;
				default:
					ps.println(">>> ERROR: INVALID OPTION SELECTED");
					break;
				}
			} while (option == NO_SELECTION);
		}
	}

	public void printPromotionMenu() {
		ps.println(">>> SELECT AN OPTION BELOW");
		ps.println(">>>");
		ps.println(">>> 1. PROMOTE TO REGULAR EMPLOYEE");
		ps.println(">>> 2. PROMOTE TO INTERN");
		ps.println(">>> 3. PROMOTE TO MANAGER");
		ps.println(">>> 4. PROMOTE TO DIRECTOR");
	}

	public void promoteToManager(Employee em, DegreeType degree) {

	}

	public void promoteToDirector() {

	}

	public void promoteToIntern(Employee em, int gpa) {

	}

	public void promoteToRegularEmployee(Employee em) {

	}

	public static void main(String[] args) {
		CorpMain program = new CorpMain();
		program.run();
	}

}
