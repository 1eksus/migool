package migool.host.auth.impl;

import migool.host.auth.IAuthenticable;

/**
 * 
 * @author Denis Migol
 *
 */
public class HostLoginPassword implements IAuthenticable {

	public final String host;
	public final String login;
	public final String password;

	/**
	 * 
	 * @param host
	 * @param login
	 * @param password
	 */
	public HostLoginPassword(String host, String login, String password) {
		this.host = host;
		this.login = login;
		this.password = password;
	}
}
