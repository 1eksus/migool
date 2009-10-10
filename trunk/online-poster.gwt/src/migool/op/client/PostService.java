package migool.op.client;

import java.util.List;

import migool.op.client.serializable.PostSerializable;
import migool.op.server.jdo.persist.HostConfig;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author Denis Migol
 * 
 */
@RemoteServiceRelativePath("post")
public interface PostService extends RemoteService {
	List<String> getCategories();

	void setPost(PostSerializable post);

	List<String> getHosts();

	HostConfig getHostConfig(String host);

	void setHostConfig(HostConfig hostConfig);

	void post(String host);
}
