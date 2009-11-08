package migool.ot.client;

import java.util.Map;

import migool.ot.client.serializable.PostHostConfig;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ConfigServiceAsync {

	void getPostHostConfig(String host, AsyncCallback<PostHostConfig> callback);

	void setPostHostConfig(PostHostConfig config, AsyncCallback<Void> callback);

	void getPostHostConfigs(AsyncCallback<Map<String, PostHostConfig>> callback);

}
