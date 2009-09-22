package migool.host.auth.impl;

import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public class HostsLoginPassword {

	public final List<String> hosts;
	public final String login;
	public final String password;

	/**
	 * 
	 * @param hosts
	 * @param login
	 * @param password
	 */
	public HostsLoginPassword(List<String> hosts, String login, String password) {
		this.hosts = hosts;
		this.login = login;
		this.password = password;
	}
}
