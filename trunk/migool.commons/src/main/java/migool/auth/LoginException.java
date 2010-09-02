package migool.auth;

/**
 * 
 * @author Denis Migol
 *
 */
public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7970141879730134984L;

	public LoginException() {
		super();
	}

	public LoginException(final String message) {
		super(message);
	}

	public LoginException(final Throwable cause) {
		super(cause);
	}

	public LoginException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
