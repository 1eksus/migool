package migool.op.server;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.jdo.PersistenceManager;

import migool.op.client.PostService;
import migool.op.client.serializable.PostSerializable;
import migool.op.server.jdo.PMF;
import migool.op.server.jdo.persist.HostConfig;
import migool.post.Post;
import migool.post.category.Categories;
import migool.post.category.Category;
import migool.post.internal.Image;
import migool.poster.IPoster;
import migool.poster.Posters;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Denis Migol
 * 
 */
@SuppressWarnings("serial")
public class PostServiceImpl extends RemoteServiceServlet implements PostService {

	PersistenceManager pm = PMF.get().getPersistenceManager();
	private Post post;
	private static final TreeMap<String, IPoster> posters = Posters.get();
	private static final List<String> hosts = Posters.getHosts();
	private TreeMap<String, HostConfig> hostConfigs = new TreeMap<String, HostConfig>();

	public PostServiceImpl() {
		super();
		post = new Post();
	}

	public List<String> getCategories() {
		List<String> ret = new ArrayList<String>();
		for (Category cat : Categories.CATS) {
			ret.add(cat.name);
		}
		return ret;
	}

	public List<String> getHosts() {
		return hosts;
	}

	@Override
	public void setPost(PostSerializable clientPost) {
		this.post.title = clientPost.title;
		this.post.title = clientPost.title;
		this.post.url = clientPost.url;
		//public List<String> categories;
		//public Image image; // TODO

		try {
			Image image = new Image();
			image.fileName = clientPost.image;
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	@Override
	public HostConfig getHostConfig(String host) {
		return hostConfigs.get(host);
	}

	@Override
	public void setHostConfig(HostConfig hostConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void post(String host) {
		// TODO Auto-generated method stub
		posters.get(host).post(post);
	}
}
