package migool.host.auth.impl;

import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public class HostLoginsPasswords {

	public final String host;
	public final List<String> logins;
	public final List<String> passwords;

	/**
	 * @param host
	 * @param logins
	 * @param passwords
	 */
	public HostLoginsPasswords(String host, List<String> logins, List<String> passwords) {
		super();
		this.host = host;
		this.logins = logins;
		this.passwords = passwords;
	}
}
