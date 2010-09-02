package migool.tor;

/**
 * 
 * @author Denis Migol
 * 
 */
public class TorClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6971134124991807547L;

	public TorClientException() {
	}

	public TorClientException(String message) {
		super(message);
	}

	public TorClientException(Throwable cause) {
		super(cause);
	}

	public TorClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
