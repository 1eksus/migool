package migool.op.client;

import java.util.List;

import migool.op.client.serializable.PostSerializable;

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
	List<String> getPosters();
}
