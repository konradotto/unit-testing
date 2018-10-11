package sem.gu.main;

import java.util.ArrayList;

import sem.gu.classes.*;
import sem.gu.enums.DegreeType;
import sem.gu.enums.Department;
import sem.gu.enums.EnumOperations;

public class CorpMain {

	// GLOBAL VARIABLES
	private IOManager io;
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
		io = new IOManager(System.in, System.out);
		this.dirBenefit = DEFAULT_DIRECTOR_BENEFIT;
		this.numEmployees = 0;
		this.employees = new ArrayList<Employee>();

	}

	/**
	 *  Function to clean-up (close the scanner) at the end of runtime
	 */
	private void close() {
		io.close();
	}

	// --- Retrieve Employee

	public Employee retrieveEmployee(int id) {
		for (Employee em : employees) {
			if (em.equals(id)) {
				io.printf(">>> EMPLOYEE %d FOUND%n", id);
				return em;
			}
		}
		io.printf(">>>ERROR: AN EMPLOYEE OF ID %d IS NOT REGISTERED IN THE SYSTEM%n", id);
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
		io.println("==<*>== WELCOME TO REUSAX CORP EMPLOYEE SYSTEM ==<*>==");
		int option;
		do {
			printMenu();
			option = io.inputInteger(">>> TYPE OPTION NUMBER");
			switch (option) {
			case REGISTER:
				io.printf(">>> OPTION %d SELECTED: REGISTERING AN EMPLOYEE%n", REGISTER);
				io.println(">>>");
				Employee employee = registerEmployee();
				numEmployees += 1;
				employees.add(employee);
				io.printf(">>> EMPLOYEE REGISTERED. THERE IS NOW %d EMPLOYEE(S) REGISTERED%n", numEmployees);
				break;
			case REMOVE:
				io.printf(">>> OPTION %d SELECTED: REMOVING AN EMPLOYEE%n", REMOVE);
				io.println(">>>");
				removeEmployee();
				break;
			case RETRIEVE:
				io.printf(">>> OPTION %d SELECTED: PRINTING EMPLOYEE DETAILS%n", RETRIEVE);
				io.println(">>>");
				int id = io.inputInteger(">>> ENTER THE ID OF THE EMPLOYEE");
				Employee em = retrieveEmployee(id);
				if (em != null) {
					io.println(em.toString());
				}
				break;
			case UPDATE_INFO:
				io.printf(">>> OPTION %d SELECTED: UPDATE EMPLOYEE INFO", UPDATE_INFO);
				io.println(">>>");
				updateInfo();
				break;
			case DIR_BENEFIT:
				io.printf(">>> OPTION %d SELECTED: SET DIRECTOR BENEFITS%n", DIR_BENEFIT);
				io.println(">>>");
				setDirectorBenefits();
				break;
			case QUIT:
				io.printf(">>> OPTION %d SELECTED: EXITING SYSTEM%n", QUIT);
				io.println(">>> HAVE A NICE DAY...");
				break;
			default:
				io.println(">>> ERROR: INVALID OPTION SELECTED");
				break;
			}
		} while (option != QUIT);
	}

	public void printMenu() {
		io.println(">>>");
		io.println(">>> CHOOSE AN OPTION BELOW");
		io.println(">>>");
		io.printf(">>> %d. REGISTER AN EMPLOYEE%n", REGISTER);
		io.printf(">>> %d. REMOVE AN EMPLOYEE%n", REMOVE);
		io.printf(">>> %d. PRINT AN EMPLOYEE'S DETAILS%n", RETRIEVE);
		io.printf(">>> %d. UPDATE EMPLOYEE DETAILS%n", UPDATE_INFO);
		io.printf(">>> %d. SET DIRECTOR BENEFIT%n", DIR_BENEFIT);
		io.printf(">>> %d. QUIT%n", QUIT);
		io.println(">>>");
	}

	// --- Create Employee

	public Employee registerEmployee() {
		Employee employee = null;
		int option = NO_SELECTION;
		int id = io.inputInteger(">>> ENTER EMPLOYEES IDENTIFICATION NUMBER");
		if (checkEmployeeExists(id)) {
			io.println(">>> THIS EMPLOYEE IS ALREADY REGISTERED, CANCELLING REGISTRATION");
		} else {
			do {
				String name = io.inputString(">>> PLEASE ENTER EMPLOYEES NAME");
				double grossSalary = io.inputDouble(">>> PLEASE ENTER EMPLOYEES GROSS SALARY");
				printEmployeeRegisterMenu();
				option = io.inputInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case EMPLOYEE:
					io.println(">>> ATTEMPTING TO CREATE EMPLOYEE...");
					employee = new RegularEmployee(name, grossSalary, id);
					io.println(">>> EMPLOYEE CREATION SUCCESSFUL");
					break;
				case INTERN:
					io.println(">>> ATTEMPTING TO CREATE INTERN...");
					employee = createIntern(name, grossSalary, id);
					io.println(">>> INTERN CREATION SUCCESSFUL");
					break;
				case MANAGER:
					io.println(">>> ATTEMPTING TO CREATE MANAGER...");
					employee = createManager(name, grossSalary, id);
					io.println(">>> MANAGER CREATION SUCCESSFUL");
					break;
				case DIRECTOR:
					io.println(">>> ATTEMPTING TO CREATE DIRECTOR...");
					employee = createDirector(name, grossSalary, id);
					io.println(">>> DIRECTOR CREATION SUCCESSFUL");
					break;
				default:
					io.println(">>> ERROR: INVALID OPTION SELECTED");
					option = NO_SELECTION;
					break;
				}
			} while (option == NO_SELECTION);
		}
		return employee;
	}

	public void printEmployeeRegisterMenu() {
		io.println(">>> CHOOSE WHAT TYPE OF EMPLOYEE YOU WISH TO ADD");
		io.println(">>> 1. REGULAR EMPLOYEE");
		io.println(">>> 2. INTERN");
		io.println(">>> 3. MANAGER");
		io.println(">>> 4. DIRECTOR");
	}

	public Intern createIntern(String name, double grossSalary, int id) {
		int gpa = NO_SELECTION;
		do {
			gpa = io.inputInteger(">>> PLEASE ENTER THE GPA (0-10) OF THE INTERN");
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
			String dt = io.inputString(">>> ENTER THE DEGREE CLASSIFICATION OF THE EMPLOYEE (BSC/MSC/PHD)");
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
			String dt = io.inputString(">>> ENTER THE DEPARTMENT OF THE EMPLOYEE (HR/TECH/BUSI)");
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
		int id = io.inputInteger(">>> ENTER THE ID OF THE EMPLOYEE TO DELETE");
		int index = NO_SELECTION;

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).equals(id))
				;
			io.println(">>> EMPLOYEE FOUND. PREPARING DELETION");
			index = i;
		}

		if (index == NO_SELECTION) {
			io.println(">>> ERROR: EMPLOYEE NOT FOUND. DELETION CANCELLED");
		} else {
			employees.remove(index);
			numEmployees--;
			// TODO clean up arraylist
			io.println(">>> EMPLOYEE DELETED");
		}
	}

	// --- Set Director Benefits

	public void setDirectorBenefits() {
		double benefit = io.inputDouble(">>> ENTER NEW BENEFIT AMOUNT");
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).isDirector()) {
				Director dir = (Director) employees.get(i);
				dir.setBenefit(benefit);
				io.printf(">>> DIRECTOR %d NEW BENEFIT SET%n", dir.getId());
			}
		}
	}

	// --- Update Employee Information

	public void updateInfo() {
		int id = io.inputInteger(">>> PLEASE ENTER THE ID OF THE EMPLOYEE YOU WANT TO UPDATE");
		Employee em = retrieveEmployee(id);
		if (em != null) {
			printUpdateMenu();
			int option = NO_SELECTION;
			do {
				option = io.inputInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case NAME:
					io.println(">>> UPDATING EMPLOYEE NAME...");
					String name = io.inputString(">>> ENTER NEW NAME");
					em.setName(name);
					io.println(">>> NAME UPDATED");
					break;
				case SALARY:
					io.println(">>> UPDATING EMPLOYEE SALARY...");
					double salary = io.inputDouble(">>> ENTER NEW SALARY");
					em.setGrossSalary(salary);
					io.println(">>> SALARY UPDATED");
					break;
				}
			} while (option == NO_SELECTION);
		}
	}

	public void printUpdateMenu() {
		io.println(">>> PLEASE SELECT AN OPTION BELOW");
		io.println(">>>");
		io.println(">>> 1. UPDATE NAME");
		io.println(">>> 2. UPDATE GROSS SALARY");
	}

	// --- Promotions

	public void promote() {
		int id = io.inputInteger(">>> ENTER EMPLOYEE ID NUMBER");
		Employee em = retrieveEmployee(id);
		if (em != null) {
			printPromotionMenu();
			int option = NO_SELECTION;
			do {
				option = io.inputInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case EMPLOYEE:
					promoteToRegularEmployee(em);
					break;
				case INTERN:
					int gpa = io.inputInteger(">>> ENTER GPA (0-10)");
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
					io.println(">>> ERROR: INVALID OPTION SELECTED");
					break;
				}
			} while (option == NO_SELECTION);
		}
	}

	public void printPromotionMenu() {
		io.println(">>> SELECT AN OPTION BELOW");
		io.println(">>>");
		io.println(">>> 1. PROMOTE TO REGULAR EMPLOYEE");
		io.println(">>> 2. PROMOTE TO INTERN");
		io.println(">>> 3. PROMOTE TO MANAGER");
		io.println(">>> 4. PROMOTE TO DIRECTOR");
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
