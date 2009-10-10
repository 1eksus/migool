package migool.op.client;

import java.util.List;

import migool.op.client.serializable.PostSerializable;
import migool.op.server.jdo.persist.HostConfig;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Denis Migol
 *
 */
public interface PostServiceAsync {
	void getCategories(AsyncCallback<List<String>> callback);
	void setPost(PostSerializable post, AsyncCallback<Void> callback);
	void getHosts(AsyncCallback<List<String>> callback);
	void getHostConfig(String host, AsyncCallback<HostConfig> callback);
	void setHostConfig(HostConfig hostConfig, AsyncCallback<Void> callback);
	void post(String host, AsyncCallback<Void> callback);
}
