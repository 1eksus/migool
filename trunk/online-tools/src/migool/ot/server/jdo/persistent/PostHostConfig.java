package migool.ot.server.jdo.persistent;

import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import migool.ot.client.serializable.GrabHostConfig;

/**
 * 
 * @author Denis Migol
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PostHostConfig {
	@PrimaryKey
	@Persistent
	private String host;
	@Persistent
	private String username;
	@Persistent
	private String password;
	@Persistent
	private boolean enabled;
	@Persistent
	private List<GrabHostConfig> grabHostConfigs;

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param grabHostConfigs the grabHostConfigs to set
	 */
	public void setGrabHostConfigs(List<GrabHostConfig> grabHostConfigs) {
		this.grabHostConfigs = grabHostConfigs;
	}

	/**
	 * @return the grabHostConfigs
	 */
	public List<GrabHostConfig> getGrabHostConfigs() {
		return grabHostConfigs;
	}
}
