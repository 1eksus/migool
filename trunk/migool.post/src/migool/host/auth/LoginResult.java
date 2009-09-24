package migool.host.auth;

/**
 * 
 * @author Denis Migol
 *
 */
public final class LoginResult {
	public static final int OK = 0;
	public static final int ERROR = 1;
	public static final int NOT_LOGGED = 2;

	private int code;

	public LoginResult(int code) {
		super();
		this.setCode(code);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}	
}
