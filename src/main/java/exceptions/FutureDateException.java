package exceptions;

public class FutureDateException extends CurrencyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FutureDateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FutureDateException(String message) {
		super(message);
	}

	public FutureDateException(Throwable cause) {
		super(cause);
	}
	
	public FutureDateException() {
		super();
	}
}
