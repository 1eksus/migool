package migool.op.client.serializable;

import java.io.Serializable;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public class HostConfigSerializable implements Serializable {
	public String host;
	public String username;
	public String password;
	public boolean enabled;
}
