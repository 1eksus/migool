package migool.op.server;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import migool.op.client.PostService;
import migool.op.client.serializable.PostSerializable;
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

	public List<String> getCategories() {
		List<String> ret = new ArrayList<String>();
		for (Category cat : Categories.CATS) {
			ret.add(cat.name);
			System.out.println(cat.name);
		}
		return ret;
	}

	public List<String> getHosts() {
		return new ArrayList<String>(hosts.keySet());
	}

	@Override
	public void setPost(PostSerializable clientPost) {
		this.post.title = clientPost.title;
		this.post.title = clientPost.title;
		this.post.url = clientPost.url;
		//public List<String> categories;
		//public Image image; // TODO

		this.post.begStory = clientPost.begStory;
		this.post.endStory = clientPost.endStory;

		this.post.name = clientPost.name;
		this.post.originalName = clientPost.originalName;
		this.post.version = clientPost.version;
		this.post.year = clientPost.year;
		this.post.format = clientPost.format;
		this.post.language = clientPost.language;
		this.post.size = clientPost.size;
		//public List<Image> screens; // TODO
		//public String fileLinks;
		//public String tags;

		// Warez
		this.post.developer = clientPost.developer;
		this.post.os = clientPost.os;
		this.post.platform = clientPost.platform;
		this.post.free = clientPost.free;
		this.post.crack = clientPost.crack;
	}
}
