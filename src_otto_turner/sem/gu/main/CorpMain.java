package sem.gu.main;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import sem.gu.classes.Employee;
import sem.gu.exceptions.NegativeInputException;

public class CorpMain {
	
	//GLOBAL VARIABLES
	private PrintStream ps;
	private Scanner scan;
	private Employee[] employees;
	private int numEmployees;
	
	//CONSTANTS
	private final int NO_SELECTION = -1;
	private final int MAX_NUM_EMPLOYEES = 10;
	
	//MENU CONSTANTS
	private final int REGISTER = 1;
	private final int REMOVE = 2;
	private final int RETRIEVE = 3;
	private final int QUIT = 4;
	
	public CorpMain() {
		ps = new PrintStream(System.out);
		scan = new Scanner(System.in);
		
		numEmployees = 0;
		this.employees = new Employee[MAX_NUM_EMPLOYEES];
		
	}
	
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
				case QUIT:
					ps.printf(">>> OPTION %d SELECTED: EXITING SYSTEM%n",QUIT);
					ps.println(">>> HAVE A NICE DAY...");
					break;
				default:
					break;
			}
		} while (option != QUIT);
	}
	
	public void printMenu() {
		ps.println(">>> WELCOME TO REUSAX CORP EMPLOYEE SYSTEM.");
		ps.println(">>> CHOOSE AN OPTION BELOW.");
		ps.println(">>>");
		ps.println(">>> 1. REGISTER AN EMPLOYEE.");
		ps.println(">>> 2. REMOVE AN EMPLOYEE.");
		ps.println(">>> 4. QUIT.");
	}
	
	public Employee registerEmployee() {
		String name = inputString(">>> PLEASE ENTER EMPLOYEES NAME");
		double grossSalary = inputDouble(">>> PLEASE ENTER EMPLOYEES GROSS SALARY");
		
		ps.println(">>> ATTEMPTING TO CREATE EMPLOYEE...");
		Employee employee = new Employee(name,grossSalary);
		ps.println(">>> EMPLOYEE CREATION SUCCESSFULL");
		return employee;
	}
	
	public void removeEmployee() {
		
	}
	
	public static void main(String[] args) {
		CorpMain program = new CorpMain();
		program.run();
	}
	
}
