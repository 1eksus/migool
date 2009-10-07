package migool.op.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Denis Migol
 *
 */
public interface PostServiceAsync {
	void getCategories(AsyncCallback<List<String>> callback);
	//void setPost(PostSerializable post, AsyncCallback<Void> callback);
	void getHosts(AsyncCallback<List<String>> callback);
}
