package migool.host.auth;

/**
 * 
 * @author Denis Migol
 *
 */
public class LoginPassword {
	private String login;
	private String password;
	
	public LoginPassword(String login, String password) {
		super();
		this.setLogin(login);
		this.setPassword(password);
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
