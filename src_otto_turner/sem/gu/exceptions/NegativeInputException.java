package sem.gu.exceptions;

public class NegativeInputException extends Throwable {
	
	public NegativeInputException() {
		super("Entered value cannot be negative");
	}
	
}
