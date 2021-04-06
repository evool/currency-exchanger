package exceptions;

@SuppressWarnings("serial")
public class InvalidDateException extends CurrencyException {

	public InvalidDateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidDateException(String message) {
		super(message);
	}

	public InvalidDateException(Throwable cause) {
		super(cause);
	}
	
	public InvalidDateException() {
		super();
	}
}
