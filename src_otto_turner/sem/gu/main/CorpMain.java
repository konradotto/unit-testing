package sem.gu.main;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import sem.gu.exceptions.NegativeInputException;

public class CorpMain {
	
	//GLOBAL VARIABLES
	PrintStream ps;
	Scanner scan;
	
	//CONSTANTS
	private final int NO_SELECTION = -1;
	
	//MENU CONSTANTS
	private final int REGISTER = 1;
	private final int REMOVE = 2;
	private final int RETRIEVE = 3;
	private final int QUIT = 4;
	
	public CorpMain() {
		ps = new PrintStream(System.out);
		scan = new Scanner(System.in);
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
	
	public void run() {
		int option;
		printMenu();
		do {
			option = inputInteger(">>> TYPE OPTION NUMBER");
			
			switch(option) {
				case REGISTER:
					break;
				case REMOVE:
					break;
				case RETRIEVE:
					break;
				case QUIT:
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
		ps.println(">>> 1.  REGISTER AN EMPLOYEE.");
		ps.println(">>> 2.  REMOVE AN EMPLOYEE.");
		ps.println(">>> 4. QUIT.");
	}
	
	public static void main(String[] args) {
		CorpMain program = new CorpMain();
		program.run();
	}
	
}
