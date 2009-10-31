package migool.op.client;

import java.util.List;
import java.util.Map;

import migool.op.client.serializable.HostConfigSerializable;
import migool.op.client.serializable.PostResponseSerializable;
import migool.op.client.serializable.PostSerializable;
import migool.op.client.serializable.PostInfoSerializable;

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

	void getHostConfig(String host, AsyncCallback<HostConfigSerializable> callback);

	void setHostConfig(HostConfigSerializable hostConfig, AsyncCallback<Void> callback);

	void getHostConfigs(AsyncCallback<Map<String, HostConfigSerializable>> callback);

	void getPostInfo(String host, AsyncCallback<PostInfoSerializable> callback);

	void post(String host, Map<String, String> props, AsyncCallback<PostResponseSerializable> callback);
}
