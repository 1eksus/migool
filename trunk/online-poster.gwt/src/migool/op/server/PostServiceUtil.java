package migool.op.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import migool.entity.ImageEntity;
import migool.http.client.HttpClientFactory;
import migool.op.client.serializable.HostConfigSerializable;
import migool.op.client.serializable.PostResponseSerializable;
import migool.op.server.jdo.persist.HostConfig;
import migool.post.internal.Image;
import migool.poster.PostResponse;
import migool.util.EmptyChecker;
import migool.util.IOUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class PostServiceUtil {
	private PostServiceUtil() {
	}
	
	private static final Logger log = Logger.getLogger(PostServiceUtil.class.getName());

	private static final HttpClient client = HttpClientFactory.newInstance().newHttpClient();
	
	private static final HashMap<String, String> contentTypes;
	
	static {
		contentTypes = new HashMap<String, String>();
		contentTypes.put("image/pjpeg", ImageEntity.JPEG);
		contentTypes.put("image/jpeg", ImageEntity.JPEG);
		contentTypes.put("image/gif", ImageEntity.GIF);
		contentTypes.put("image/bmp", ImageEntity.BMP);
		contentTypes.put("image/tiff", ImageEntity.TIFF);
		contentTypes.put("image/x-png", ImageEntity.PNG);
		contentTypes.put("image/png", ImageEntity.PNG);
	}

	/**
	 * 
	 * @param hostConfig
	 * @return
	 */
	public static final HostConfigSerializable toHostConfigSerializable(HostConfig hostConfig) {
		if (hostConfig == null) {
			return null;
		}
		HostConfigSerializable ret = new HostConfigSerializable();
		ret.host = hostConfig.getHost();
		ret.username = hostConfig.getUsername();
		ret.password = hostConfig.getPassword();
		ret.enabled = hostConfig.isEnabled();
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public static final List<HostConfigSerializable> toListHostConfigSerializable(Collection<HostConfig> hostConfigs) {
		if (hostConfigs == null) {
			return null;
		}
		ArrayList<HostConfigSerializable> ret = new ArrayList<HostConfigSerializable>(hostConfigs.size());
		for (HostConfig hostConfig : hostConfigs) {
			ret.add(toHostConfigSerializable(hostConfig));
		}
		return ret;
	}

	/**
	 * 
	 * @param hostConfig
	 * @return
	 */
	public static final HostConfig toHostConfig(HostConfigSerializable hostConfig) {
		if (hostConfig == null) {
			return null;
		}
		HostConfig ret = new HostConfig();
		ret.setHost(hostConfig.host);
		ret.setUsername(hostConfig.username);
		ret.setPassword(hostConfig.password);
		ret.setEnabled(hostConfig.enabled);
		return ret;
	}

	/**
	 * 
	 * @param contentType
	 * @return
	 */
	private static final String getType(String contentType) {
		if (EmptyChecker.isNullOrEmpty(contentType)) {
			return null;
		}
		return contentTypes.get(contentType);
	}

	/**
	 * 
	 * @return
	 */
	private static final String getFileExtension(String type) {
		if (EmptyChecker.isNotNullOrEmpty(type)) {
			return "." + type;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param imageUrl
	 * @param prefix
	 * @return
	 */
	public static final Image getImage(String imageUrl, String prefixName) {
		try {
			System.out.println(imageUrl);
			HttpGet get = new HttpGet(imageUrl);
			HttpEntity entity = client.execute(get).getEntity();
			Image ret = new Image();
			ret.bytes = IOUtil.toByteArray(entity.getContent());
			String type = getType(entity.getContentType().getValue());
			ret.type = type;
			String extension = getFileExtension(type);
			ret.fileName = (EmptyChecker.isNullOrEmpty(extension)) ? prefixName : prefixName + extension;
			return ret;
		} catch (Exception e) {
			log.throwing(PostServiceUtil.class.getName(), "getImage", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static final PostResponseSerializable toPostResponseSerializable(PostResponse response) {
		// TODO
		return null;
	}
}
