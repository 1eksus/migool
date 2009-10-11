package migool.op.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import migool.op.client.serializable.HostConfigSerializable;
import migool.op.server.jdo.persist.HostConfig;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class PostServiceUtil {
	private PostServiceUtil() {
	}

	/**
	 * 
	 * @param hostConfig
	 * @return
	 */
	public static final HostConfigSerializable toHostConfigSerializable(HostConfig hostConfig) {
		HostConfigSerializable ret = new HostConfigSerializable();
		ret.host = hostConfig.getHost();
		ret.username = hostConfig.getUsername();
		ret.password = hostConfig.getPassword();
		ret.enabled = hostConfig.isEnabled();
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public static final List<HostConfigSerializable> toListHostConfigSerializable(Collection<HostConfig> hostConfigs) {
		if (hostConfigs == null) {
			return null;
		}
		ArrayList<HostConfigSerializable> ret = new ArrayList<HostConfigSerializable>(hostConfigs.size());
		for (HostConfig hostConfig : hostConfigs) {
			ret.add(toHostConfigSerializable(hostConfig));
		}
		return ret;
	}
}
