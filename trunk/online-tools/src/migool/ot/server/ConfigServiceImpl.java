package migool.ot.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import migool.ot.client.ConfigService;
import migool.ot.client.serializable.PostHostConfig;
import migool.ot.server.hosts.Posters;
import migool.ot.server.jdo.JDOUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Denis Migol
 * 
 */
@SuppressWarnings("serial")
public class ConfigServiceImpl extends RemoteServiceServlet implements ConfigService {

	private final Map<String, PostHostConfig> configs;

	public ConfigServiceImpl() {
		super();
		configs = new HashMap<String, PostHostConfig>();
		List<String> posters = Posters.posters;
		for (String poster : posters) {
			configs.put(poster, JDOUtil.getPostHostConfigByHost(poster));
		}
	}

	@Override
	public PostHostConfig getPostHostConfig(String host) {
		return configs.get(host);
	}

	@Override
	public Map<String, PostHostConfig> getPostHostConfigs() {
		return new TreeMap<String, PostHostConfig>(configs);
	}

	@Override
	public void setPostHostConfig(PostHostConfig config) {
		configs.put(config.host, config);
		JDOUtil.addPostHostConfig(config);
	}
}
