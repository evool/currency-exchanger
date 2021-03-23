package exceptions;

public class InvalidDateException extends CurrencyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
