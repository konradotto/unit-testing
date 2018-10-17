package sem.gu.classes;

import sem.gu.exceptions.NegativeInputException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;


public class IOManager extends PrintStream {

    public final int NO_SELECTION = -1;

    private final Scanner scanner;

    public IOManager(InputStream inputStream, OutputStream outputStream) {
        super(outputStream);

        scanner = new Scanner(inputStream);
    }

    public void close() {
        scanner.close();
        super.close();
    }

    // --- Input Primitives

    /**
     * Print out a message to console then take an integer input
     * @param message The message to be printed to give the input context
     * @return The positive input
     */
    public int inputPositiveInteger(String message) {
        int inputInt = NO_SELECTION; // Set default values
        while (inputInt == NO_SELECTION) {
            println(message);
            try {
                inputInt = scanner.nextInt(); // attempt to take in a double
                if (inputInt < 0) {
                    // entered value cannot be negative
                    throw new NegativeInputException();
                }
            } catch (InputMismatchException ex) {
                inputInt = NO_SELECTION;
                printf("InputMismatchException caught. Your input could not be parsed into an integer%n");
            } catch (NegativeInputException ex) {
                inputInt = NO_SELECTION;
                printf("%s%n", ex.getMessage());
            } finally {
                scanner.nextLine();
            }
        }
        return inputInt;
    }

    /**
     * Print out a message to console then take a double input
     * @param message The message to be printed to give the input context
     * @return The double input
     */
    public double inputDouble(String message) {
        double inputDouble = NO_SELECTION; // Set default values
        while (inputDouble == NO_SELECTION) {
            println(message);
            try {
                inputDouble = scanner.nextDouble(); // attempt to take in a double
                if (inputDouble < 0) {
                    // entered value cannot be negative
                    throw new NegativeInputException();
                }
            } catch (InputMismatchException ex) {
                inputDouble = -1.0;
                printf("InputMismatchException caught. Your input could not be parsed into a double.%n");
            } catch (NegativeInputException ex) {
                inputDouble = -1.0;
                printf("%s%n", ex.getMessage());
            } finally {
                scanner.nextLine();
            }
        }
        return inputDouble;
    }

    /**
     * Print out a message to console then take a string input
     * @param message The message to be printed to give the input context
     * @return The string input
     */
    public String inputString(String message) {
        String inputString = ""; // Set default value
        while (inputString.equals("")) {
            println(message);
            inputString = scanner.nextLine();
            if (inputString.equals("") || inputString.equals(" ")) {
                // if entered string is empty of just a space, loop again
                inputString = "";
                println("Entered value is invalid, value must have at least 1 character that is not a space");
            }
        }
        return inputString;
    }
}
