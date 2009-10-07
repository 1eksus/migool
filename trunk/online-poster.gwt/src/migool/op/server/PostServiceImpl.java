package migool.op.server;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import migool.op.client.PostService;
import migool.op.serializable.PostSerializable;
import migool.post.Post;
import migool.post.category.Categories;
import migool.post.category.Category;
import migool.poster.HostConfig;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Denis Migol
 * 
 */
@SuppressWarnings("serial")
public class PostServiceImpl extends RemoteServiceServlet implements PostService {

	private Post post;
	private TreeMap<String, HostConfig> hosts;

	public PostServiceImpl() {
		super();
		post = null;
		hosts = new TreeMap<String, HostConfig>();
	}

	@Override
	public List<String> getCategories() {
		List<String> ret = new ArrayList<String>();
		for (Category cat : Categories.CATS) {
			ret.add(cat.name);
			System.out.println(cat.name);
		}
		return ret;
	}

	@Override
	public void setPost(PostSerializable post) {
		this.post = post;
	}

	@Override
	public List<String> getHosts() {
		// TODO Auto-generated method stub
		return null;
	}
}