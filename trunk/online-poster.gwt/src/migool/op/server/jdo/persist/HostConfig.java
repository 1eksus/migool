package migool.op.server.jdo.persist;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 
 * @author Denis Migol
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class HostConfig {
	@PrimaryKey
	@Persistent
	public String host;
	@Persistent
	public String username;
	@Persistent
	public String password;
	@Persistent
	public boolean enabled;
}
