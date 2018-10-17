package sem.gu.main;

import java.util.ArrayList;

import sem.gu.classes.*;
import sem.gu.enums.DegreeType;
import sem.gu.enums.Department;
import sem.gu.enums.EnumOperations;
import sem.gu.exceptions.IllegalGPAException;

public class CorpMain {

	// GLOBAL VARIABLES
	private IOManager io;
	private ArrayList<Employee> employees;

	// CONSTANTS
	private final double DEFAULT_DIRECTOR_BENEFIT = 5000.0;
	private final String[] departments = { "HR", "TECHNICAL", "BUSINESS" };

	// MAIN MENU CONSTANTS
	private final int REGISTER = 1;
	private final int REMOVE = 2;
	private final int RETRIEVE = 3;
	private final int UPDATE_INFO = 4;
	private final int DIR_BENEFIT = 5;
	private final int PROMOTE = 6;
	private final int QUIT = 7;

	// REGISTER EMPLOYEE MENU CONSTANTS
	private final int EMPLOYEE = 1;
	private final int INTERN = 2;
	private final int MANAGER = 3;
	private final int DIRECTOR = 4;

	// UPDATE EMPLOYEE MENU CONSTANTS
	private final int NAME = 1;
	private final int SALARY = 2;
	private final int GRADE = 3;

	public CorpMain() {
		io = new IOManager(System.in, System.out);
		Director.setBenefit(DEFAULT_DIRECTOR_BENEFIT);
		this.employees = new ArrayList<Employee>();

	}

	/**
	 * Function to clean-up (close the scanner) at the end of runtime
	 */
	private void close() {
		io.close();
	}

	// --- Retrieve Employee

	/**
	 * Using the employee's unique id, retrieve the employee from the list of employees
	 * @param id The id of the employee to be retrieved
	 * @return The retrieved employee if it exists otherwise, NULL
	 */
	public Employee retrieveEmployee(int id) {
		Employee returnEmp = null;
		int i = 0;
		while (i < employees.size() && returnEmp == null) {
			Employee tempEmp = employees.get(i);
			if (tempEmp.getId() == id) {
				io.printf(">>> EMPLOYEE %d FOUND%n", id);
				returnEmp = tempEmp;
			}
			++i;
		}

		if (returnEmp == null) {
			io.printf(">>>ERROR: AN EMPLOYEE OF ID %d IS NOT REGISTERED IN THE SYSTEM%n", id);
		}

		return returnEmp;
	}

	// --- Check Existing

	/**
	 * Check if an employee exists in the list of employees
	 * @param id The id of the employee to check
	 * @return TRUE if the employee exists, otherwise FALSE
	 */
	public boolean checkEmployeeExists(int id) {
		boolean exists = false;
		int i = 0;
		while (i < employees.size() && exists == false) {
			if (employees.get(i).getId() == id) {
				exists = true;
			}
			++i;
		}
		return exists;
	}

	// --- Main Menu

	/**
	 * Print the main menu and take option input then execute option. Repeat until quit option is selected
	 */
	public void run() {
		io.println("==<*>== WELCOME TO REUSAX CORP EMPLOYEE SYSTEM ==<*>==");
		int option;
		do {
			printMenu();
			option = io.inputPositiveInteger(">>> TYPE OPTION NUMBER");
			switch (option) {
			case REGISTER:
				io.printf(">>> OPTION %d SELECTED: REGISTERING AN EMPLOYEE%n", REGISTER);
				io.println(">>>");
				Employee employee = registerEmployee();
				if (employee != null) {
					employees.add(employee);
					io.printf(">>> EMPLOYEE REGISTERED. THERE IS NOW %d EMPLOYEE(S) REGISTERED%n", employees.size());
				}
				break;
			case REMOVE:
				io.printf(">>> OPTION %d SELECTED: REMOVING AN EMPLOYEE%n", REMOVE);
				io.println(">>>");
				removeEmployee();
				break;
			case RETRIEVE:
				io.printf(">>> OPTION %d SELECTED: PRINTING EMPLOYEE DETAILS%n", RETRIEVE);
				io.println(">>>");
				int id = io.inputPositiveInteger(">>> ENTER THE ID OF THE EMPLOYEE");
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
			case PROMOTE:
				io.printf(">>> OPTION %d SELECTED: PROMOTING EMPLOYEE%n", PROMOTE);
				io.println(">>>");
				promote();
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
		close();
	}

	/**
	 * Print the main menu
	 */
	public void printMenu() {
		io.println(">>>");
		io.println(">>> CHOOSE AN OPTION BELOW");
		io.println(">>>");
		io.printf(">>> %d. REGISTER AN EMPLOYEE%n", REGISTER);
		io.printf(">>> %d. REMOVE AN EMPLOYEE%n", REMOVE);
		io.printf(">>> %d. PRINT AN EMPLOYEE'S DETAILS%n", RETRIEVE);
		io.printf(">>> %d. UPDATE EMPLOYEE DETAILS%n", UPDATE_INFO);
		io.printf(">>> %d. SET DIRECTOR BENEFIT%n", DIR_BENEFIT);
		io.printf(">>> %d. PROMOTE AN EMPLOYEE%n", PROMOTE);
		io.printf(">>> %d. QUIT%n", QUIT);
		io.println(">>>");
	}

	// --- Create Employee

	/**
	 * Print menu and allow user to select what type of user to create
	 * @return The created employee or null if the desired entered id matches an existing employee
	 */
	public Employee registerEmployee() {
		Employee employee = null;
		int option;
		int id = io.inputPositiveInteger(">>> ENTER EMPLOYEES IDENTIFICATION NUMBER");
		if (checkEmployeeExists(id)) {
			io.println(">>> THIS EMPLOYEE IS ALREADY REGISTERED, CANCELLING REGISTRATION");
		} else {
			String name;
			double basicGrossSalary;
			name = io.inputString(">>> PLEASE ENTER EMPLOYEES NAME");
			basicGrossSalary = io.inputDouble(">>> PLEASE ENTER EMPLOYEES GROSS SALARY");
			do {
				printEmployeeRegisterMenu();
				option = io.inputPositiveInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case EMPLOYEE:
					io.println(">>> ATTEMPTING TO CREATE EMPLOYEE...");
					employee = new Employee(name, basicGrossSalary, id);
					io.println(">>> EMPLOYEE CREATION SUCCESSFUL");
					break;
				case INTERN:
					io.println(">>> ATTEMPTING TO CREATE INTERN...");
					employee = createIntern(name, basicGrossSalary, id);
					io.println(">>> INTERN CREATION SUCCESSFUL");
					break;
				case MANAGER:
					io.println(">>> ATTEMPTING TO CREATE MANAGER...");
					employee = createManager(name, basicGrossSalary, id);
					io.println(">>> MANAGER CREATION SUCCESSFUL");
					break;
				case DIRECTOR:
					io.println(">>> ATTEMPTING TO CREATE DIRECTOR...");
					employee = createDirector(name, basicGrossSalary, id);
					io.println(">>> DIRECTOR CREATION SUCCESSFUL");
					break;
				default:
					io.println(">>> ERROR: INVALID OPTION SELECTED");
					option = io.NO_SELECTION;
					break;
				}
			} while (option == io.NO_SELECTION);
		}
		return employee;
	}

	/**
	 * Print the register employee menu
	 */
	public void printEmployeeRegisterMenu() {
		io.println(">>> CHOOSE WHAT TYPE OF EMPLOYEE YOU WISH TO ADD");
		io.println(">>> 1. REGULAR EMPLOYEE");
		io.println(">>> 2. INTERN");
		io.println(">>> 3. MANAGER");
		io.println(">>> 4. DIRECTOR");
		io.println(">>>");
	}

	/**
	 * Create an intern employee
	 * @param name The name of the employee
	 * @param basicGrossSalary The gross salary of the employee
	 * @param id The employee's unique id
	 * @return The created intern
	 */
	public Intern createIntern(String name, double basicGrossSalary, int id) {
		GPA gpa = readGPA();
		Intern intern = new Intern(name, basicGrossSalary, id, gpa);
		return intern;
	}

	/**
	 * Read a gpa and check thats within the required bounds
	 * @return The entered and validated gpa
	 */
	public GPA readGPA() {
		GPA gpa = null;
		while (gpa == null) {
			try {
				gpa = new GPA(io.inputPositiveInteger(String.format(">>> PLEASE ENTER THE GPA (%d-%d) OF THE INTERN",
						GPA.getMinGpa(), GPA.getMaxGpa())));
			} catch (IllegalGPAException e) {
				e.printStackTrace(io);
			}
		}

		return gpa;
	}

	/**
	 * Create a manager employee
	 * @param name The name of the employee
	 * @param basicGrossSalary The gross salary of the employee
	 * @param id The employee's unique id
	 * @return The created manager
	 */
	public Manager createManager(String name, double basicGrossSalary, int id) {
		DegreeType degree = retrieveDegreeType();
		Manager manager = new Manager(name, basicGrossSalary, id, degree);
		return manager;
	}

	/**
	 * Create a director employee
	 * @param name The name of the employee
	 * @param basicGrossSalary The gross salary of the employee
	 * @param id The employee's unique id
	 * @return The created director
	 */
	public Director createDirector(String name, double basicGrossSalary, int id) {
		DegreeType degree = retrieveDegreeType();
		Department department = retrieveDepartment();
		Director director = new Director(name, basicGrossSalary, id, degree, department);
		return director;
	}

	/**
	 * Take input of a degree and match it to the degree enum
	 * @return The degree classification matched to the input of the user
	 */
	public DegreeType retrieveDegreeType() {
		DegreeType degree;
		do {
			String dt = io.inputString(">>> ENTER THE DEGREE CLASSIFICATION OF THE EMPLOYEE (BSC/MSC/PHD)");
			degree = EnumOperations.searchEnum(DegreeType.class, dt);

			if (degree == null) {
				io.println(">>> ERROR: ILLEGAL DEGREE CLASSIFICATION. TRY AGAIN");
			}
		} while (degree == null);
		return degree;
	}

	/**
	 * Take input of a department and match it to the department enum
	 * @return The department matched to the input of the user
	 */
	public Department retrieveDepartment() {
		Department department;
		do {
			String dep = io.inputString(String.format(">>> ENTER THE DEPARTMENT OF THE EMPLOYEE (%s/%s/%s)",
					departments[0], departments[1], departments[2]));

			department = EnumOperations.searchEnum(Department.class, dep);

			if (department == null) {
				io.println(">>> ERROR: ILLEGAL DEPARTMENT. TRY AGAIN");
				department = Department.NA;
			}
		} while (department == Department.NA);
		return department;
	}

	// --- Remove Employee

	/**
	 * Attempt to remove a employee from the list of employees using an entered id
	 */
	public void removeEmployee() {
		int id = io.inputPositiveInteger(">>> ENTER THE ID OF THE EMPLOYEE TO DELETE");
		int index = io.NO_SELECTION;

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).equals(id))
				;
			io.println(">>> EMPLOYEE FOUND. PREPARING DELETION");
			index = i;
		}

		if (index == io.NO_SELECTION) {
			io.println(">>> ERROR: EMPLOYEE NOT FOUND. DELETION CANCELLED");
		} else {
			employees.remove(index);
			io.println(">>> EMPLOYEE DELETED");
		}
	}

	// --- Set Director Benefits

	/**
	 * Set the director benefits which affect their salary
	 */
	public void setDirectorBenefits() {
		double benefit = io.inputDouble(">>> ENTER NEW BENEFIT AMOUNT");
		Director.setBenefit(benefit);
	}

	// --- Update Employee Information

	/**
	 * Print a menu and update an employees information. The information being updated is based on the option selected by the user.
	 */
	public void updateInfo() {
		int id = io.inputPositiveInteger(">>> PLEASE ENTER THE ID OF THE EMPLOYEE YOU WANT TO UPDATE");
		Employee em = retrieveEmployee(id);
		if (em != null) {
			printUpdateMenu(em.isIntern());
			int option;
			do {
				option = io.inputPositiveInteger(">>> TYPE OPTION CHOICE");
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
					em.setBasicGrossSalary(salary);
					io.println(">>> SALARY UPDATED");
					break;
				case GRADE:
					if (em.isIntern()) {
						io.println(">>> UPDATING INTERN GPA...");
						GPA gpa = readGPA();
						((Intern) em).setGpa(gpa);
						((InternSalary) em.getSalaryScheme()).setGpa(gpa);
						io.println(">>> GPA UPDATED");
						break;
					}

					// allow slip to default if not an intern
				default:
					io.println("ERROR: INVALID OPTION SELECTED. TRY AGAIN");
					option = io.NO_SELECTION;
				}
			} while (option == io.NO_SELECTION);
		}
	}

	/**
	 * Print the menu for updating info. Print additional info if the employee is an intern
	 * @param isIntern If the employee being updated is an intern
	 */
	public void printUpdateMenu(boolean isIntern) {
		io.println(">>> PLEASE SELECT AN OPTION BELOW");
		io.println(">>>");
		io.printf(">>> %d. UPDATE NAME%n", NAME);
		io.printf(">>> %d. UPDATE GROSS SALARY%n", SALARY);
		if (isIntern) {
			io.printf(">>> %d. UPDATE GPA%n", GRADE);
		}
	}

	// --- Promotions

	/**
	 * Change an employee type (intern, manager, regular employee or director) for another.
	 */
	public void promote() {
		int id = io.inputPositiveInteger(">>> ENTER EMPLOYEE ID NUMBER");
		Employee em = retrieveEmployee(id);
		if (em != null) {
			int option;
			do {
				printPromotionMenu();
				option = io.inputPositiveInteger(">>> TYPE OPTION CHOICE");
				switch (option) {
				case EMPLOYEE:
					if (!em.isRegularEmployee()) {
						promoteToRegularEmployee(em);
					} else {
						io.println(">>> ERROR: THE SELECTED EMPLOYEE IS OF THE SELECTED TYPE");
					}
					break;
				case INTERN:
					if (!em.isIntern()) {
						GPA gpa = readGPA();
						promoteToIntern(em, gpa);
					} else {
						io.println(">>> ERROR: THE SELECTED EMPLOYEE IS OF THE SELECTED TYPE");
					}
					break;
				case MANAGER:
					if (!em.isManager()) {
						DegreeType degree;
						if (em.isDirector()) {
							Director temp_em = (Director) em;
							degree = temp_em.getDegree();
						} else {
							degree = retrieveDegreeType();
						}
						promoteToManager(em, degree);
					} else {
						io.println(">>> ERROR: THE SELECTED EMPLOYEE IS OF THE SELECTED TYPE");
					}
					break;
				case DIRECTOR:
					if (!em.isDirector()) {
						DegreeType degree;
						if (em.isManager()) {
							Manager temp_em = (Manager) em;
							degree = temp_em.getDegree();
						} else {
							degree = retrieveDegreeType();
						}
						Department department = retrieveDepartment();
						promoteToDirector(em, degree, department);
					} else {
						io.println(">>> ERROR: THE SELECTED EMPLOYEE IS OF THE SELECTED TYPE");
					}
					break;
				default:
					io.println(">>> ERROR: INVALID OPTION SELECTED");
					break;
				}
			} while (option == io.NO_SELECTION);
		}
	}

	/**
	 * Print the promotion menu
	 */
	public void printPromotionMenu() {
		io.println(">>> SELECT AN OPTION BELOW");
		io.println(">>>");
		io.println(">>> 1. PROMOTE TO REGULAR EMPLOYEE");
		io.println(">>> 2. PROMOTE TO INTERN");
		io.println(">>> 3. PROMOTE TO MANAGER");
		io.println(">>> 4. PROMOTE TO DIRECTOR");
		io.println(">>>");
	}

	/**
	 * Promote the employee to a manager
	 * @param em The existing employee
	 * @param degree The degree classification required to become a manager
	 */
	public void promoteToManager(Employee em, DegreeType degree) {
		String name = em.getName();
		double basicGrossSalary = em.getBasicGrossSalary();
		int id = em.getId();

		employees.remove(em);
		employees.add(new Manager(name, basicGrossSalary, id, degree));
	}

	/**
	 * Promote the employee to a director
	 * @param em The existing employee
	 * @param deg The degree classification required to become a director
	 * @param dep The department required to become a director
	 */
	public void promoteToDirector(Employee em, DegreeType deg, Department dep) {
		String name = em.getName();
		double basicGrossSalary = em.getBasicGrossSalary();
		int id = em.getId();

		employees.remove(em);
		employees.add(new Director(name, basicGrossSalary, id, deg, dep));
	}

	/**
	 * Promote the employee to an intern
	 * @param em The existing employee
	 * @param gpa The gpa required to become an intern
	 */
	public void promoteToIntern(Employee em, GPA gpa) {
		String name = em.getName();
		double basicGrossSalary = em.getBasicGrossSalary();
		int id = em.getId();

		employees.remove(em);
		employees.add(new Intern(name, basicGrossSalary, id, gpa));
	}

	/**
	 * Promote the employee to a regular employee
	 * @param em The existing employee
	 */
	public void promoteToRegularEmployee(Employee em) {
		String name = em.getName();
		double basicGrossSalary = em.getBasicGrossSalary();
		int id = em.getId();

		employees.remove(em);
		employees.add(new Employee(name, basicGrossSalary, id));
	}

	public static void main(String[] args) {
		CorpMain program = new CorpMain();
		program.run();
	}
}
