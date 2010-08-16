package migool.auth;

import java.io.Serializable;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Login implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2014789392588228674L;

	private String username;
	private String password;

	public static class Builder {
		private final Login login = new Login();

		public static Builder newBuilder() {
			return new Builder();
		}

		public Login build() {
			return login;
		}

		public Builder setUsername(final String username) {
			login.setUsername(username);
			return this;
		}

		public Builder setPassword(final String password) {
			login.setPassword(password);
			return this;
		}

		public Builder setLogin(final Login login) {
			this.login.setUsername(login.getUsername());
			this.login.setPassword(login.getPassword());
			return this;
		}
	}

	/**
	 * 
	 */
	public Login() {
	}

	public Login(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Login)) {
			return false;
		}
		final Login other = (Login) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		return true;
	}
}
