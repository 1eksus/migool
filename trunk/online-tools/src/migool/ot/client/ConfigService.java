package migool.ot.client;

import java.util.Map;

import migool.ot.client.serializable.PostHostConfig;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author Denis Migol
 *
 */
@RemoteServiceRelativePath("config")
public interface ConfigService extends RemoteService {
	PostHostConfig getPostHostConfig(String host);

	void setPostHostConfig(PostHostConfig config);

	Map<String, PostHostConfig> getPostHostConfigs();
}
