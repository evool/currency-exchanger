package exceptions;

public class ConnectionException extends CurrencyException {

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable cause) {
		super(cause);
	}
	
	public ConnectionException() {
		super();
	}
}
