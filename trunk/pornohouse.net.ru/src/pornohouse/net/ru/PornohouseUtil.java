package pornohouse.net.ru;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import migool.grab.host.redtube.RedtubeGrab;
import migool.http.client.HttpClientFactory;
import migool.post.internal.Image;
import migool.poster.cms.ucoz.post.PublUcozPost;
import migool.util.IOUtil;

/**
 * 
 * @author Denis Migol
 *
 */
public final class PornohouseUtil {
	private PornohouseUtil() {
	}

	private static final List<Image> toListImage(List<String> urls) {
		ArrayList<Image> ret = new ArrayList<Image>(urls.size());
		HttpClient client = HttpClientFactory.newInstance().newHttpClient();
		HttpGet get = null;
		HttpResponse response = null;
		Image img = null;
		for (String url : urls) {
			get = new HttpGet(url);
			try {
				response = client.execute(get);
				img = new Image();
				img.bytes = IOUtil.toByteArray(response.getEntity().getContent());
				img.fileName = (new File(url)).getName();
				ret.add(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static final PornohousePost toPornohousePost(RedtubeGrab grab) {
		PornohousePost ret = new PornohousePost();
		ret.url = grab.share;
		ret.title = grab.title;
		// TODO categories
		// TODO brief
		ret.embed = grab.embed;
		ret.duration = grab.duration;
		String[] thumbs = grab.thumbs;
		ArrayList<String> urls = new ArrayList<String>(thumbs.length + 1);
		urls.addAll(Arrays.asList(thumbs));
		urls.add(grab.thumb);
		ret.images = toListImage(urls);
		return ret;
	}

	public static final PublUcozPost toPublUcozPost(PornohousePost post) {
		// TODO
		return null;
	}
}
