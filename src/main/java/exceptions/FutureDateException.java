package exceptions;

@SuppressWarnings("serial")
public class FutureDateException extends CurrencyException {

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
