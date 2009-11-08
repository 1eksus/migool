package migool.ot.client.serializable;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
@SuppressWarnings("serial")
public class PostHostConfig implements Serializable {

	public String host;

	public String username;

	public String password;

	public boolean enabled;

	public List<GrabHostConfig> grabHostConfigs;
}
