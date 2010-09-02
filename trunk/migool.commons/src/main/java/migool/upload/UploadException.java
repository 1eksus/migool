/**
 * 
 */
package migool.upload;

/**
 * @author Denis Migol
 *
 */
public class UploadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5271184458015478382L;

	/**
	 * 
	 */
	public UploadException() {
	}

	/**
	 * @param message
	 */
	public UploadException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UploadException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}

}
