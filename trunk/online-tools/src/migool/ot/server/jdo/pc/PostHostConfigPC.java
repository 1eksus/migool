package migool.ot.server.jdo.pc;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import migool.ot.client.serializable.PostHostConfig;

/**
 * 
 * @author Denis Migol
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PostHostConfigPC {
	@PrimaryKey
	@Persistent
	private String host;

	@Persistent(serialized = "true")
	private PostHostConfig config;
	
	public PostHostConfigPC() {
	}
	
	public PostHostConfigPC(PostHostConfig config) {
		this.host = config.host;
		this.config = config;
	}

	/**
	 * @param host
	 *            the host to set
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
	 * @param config
	 *            the config to set
	 */
	public void setConfig(PostHostConfig config) {
		this.config = config;
	}

	/**
	 * @return the config
	 */
	public PostHostConfig getConfig() {
		return config;
	}
}
