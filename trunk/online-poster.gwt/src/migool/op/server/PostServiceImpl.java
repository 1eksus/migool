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
import migool.op.client.serializable.PostResponseSerializable;
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
		Post post = new Post();
		post.title = clientPost.title;
		post.title = clientPost.title;
		post.url = clientPost.url;
		// TODO
		// public List<String> categories;
		post.image = getImage(clientPost.imageUrl, "image");
		System.out.println(post.image);

		post.begStory = clientPost.begStory;
		post.endStory = clientPost.endStory;

		post.name = clientPost.name;
		post.originalName = clientPost.originalName;
		post.version = clientPost.version;
		post.year = clientPost.year;
		post.format = clientPost.format;
		post.language = clientPost.language;
		post.size = clientPost.size;
		// public List<Image> screens; // TODO
		// public String fileLinks;
		post.tags = clientPost.tags;

		// Warez
		post.developer = clientPost.developer;
		post.os = clientPost.os;
		post.platform = clientPost.platform;
		post.free = clientPost.free;
		post.crack = clientPost.crack;

		this.post = post;
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
	public PostResponseSerializable post(String host) {
		return toPostResponseSerializable(posters.get(host).post(post));
	}
}
