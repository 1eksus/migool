package migool.op.server;

import static migool.op.server.PostServiceUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.GAEConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import migool.http.client.HttpClientFactory;
import migool.op.client.PostService;
import migool.op.client.serializable.HostConfigSerializable;
import migool.op.client.serializable.PostSerializable;
import migool.op.server.jdo.JDOUtil;
import migool.post.Post;
import migool.post.category.Categories;
import migool.post.category.Category;
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
	
	static {
		HttpClientFactory.setDefault(new HttpClientFactory() {
			
			@Override
			public HttpClient newHttpClient() {
				return new DefaultHttpClient(new GAEConnectionManager(), new BasicHttpParams());
			}
		});
	}

	private Post post;
	private static final TreeMap<String, IPoster> posters = Posters.get();
	private static final List<String> hosts = Posters.getHosts();
	private TreeMap<String, HostConfigSerializable> hostConfigs = new TreeMap<String, HostConfigSerializable>();

	public PostServiceImpl() {
		super();
		post = new Post();
		for (String host : hosts) {
			hostConfigs.put(host, toHostConfigSerializable(JDOUtil.getHostConfigByHost(host)));
		}
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
		// TODO
		// public List<String> categories;
		// public Image image; // TODO

//		try {
//			Image image = new Image();
//			image.fileName = clientPost.image;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		post.image = getImage(clientPost.imageUrl, "image");
		System.out.println(post.image);

		this.post.begStory = clientPost.begStory;
		this.post.endStory = clientPost.endStory;

		this.post.name = clientPost.name;
		this.post.originalName = clientPost.originalName;
		this.post.version = clientPost.version;
		this.post.year = clientPost.year;
		this.post.format = clientPost.format;
		this.post.language = clientPost.language;
		this.post.size = clientPost.size;
		// public List<Image> screens; // TODO
		// public String fileLinks;
		// public String tags;

		// Warez
		this.post.developer = clientPost.developer;
		this.post.os = clientPost.os;
		this.post.platform = clientPost.platform;
		this.post.free = clientPost.free;
		this.post.crack = clientPost.crack;
	}

	@Override
	public Map<String, HostConfigSerializable> getHostConfigs() {
		return hostConfigs;
	}

	@Override
	public HostConfigSerializable getHostConfig(String host) {
		return hostConfigs.get(host);
	}

	@Override
	public void setHostConfig(HostConfigSerializable hostConfig) {
		hostConfigs.put(hostConfig.host, hostConfig);
		JDOUtil.addHostConfig(toHostConfig(hostConfig));
	}

	@Override
	public void post(String host) {
		// TODO Auto-generated method stub
		posters.get(host).post(post);
	}
}
