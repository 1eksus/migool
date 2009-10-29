package pornohouse.net.ru;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.EofSensorInputStream;

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
	
	private static final byte[] toByteArray(InputStream in) throws IOException {
		if (in instanceof EofSensorInputStream) {
			EofSensorInputStream newIn = (EofSensorInputStream) in;
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int read = -1;
			while ((read = newIn.read()) > -1) {
				bo.write(read);
			}
			newIn.close();
			return bo.toByteArray();
		}
		return IOUtil.toByteArray(in);
	}

	private static final List<Image> toListImage(List<String> urls) {
		ArrayList<Image> ret = new ArrayList<Image>(urls.size());
		HttpClient client = HttpClientFactory.get().newHttpClient();
		HttpGet get = null;
		HttpResponse response = null;
		Image img = null;
		for (String url : urls) {
			get = new HttpGet(url);
			try {
				response = client.execute(get);
				img = new Image();
				//img.bytes = IOUtil.toByteArray(response.getEntity().getContent());
				img.bytes = toByteArray(response.getEntity().getContent());
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
		// TODO brief ?
		ret.embed = grab.embed;
		ret.duration = grab.duration;
		String[] thumbs = grab.thumbs;
		ArrayList<String> urls = new ArrayList<String>(thumbs.length + 1);
		urls.add(grab.thumb);
		urls.addAll(Arrays.asList(thumbs));
		ret.images = toListImage(urls);
		return ret;
	}

	public static final PublUcozPost toPublUcozPost(PornohousePost post) {
		PublUcozPost ret = new PublUcozPost();
		ret.format_message = false;
		ret.source = post.url;
		// TODO categories
		ret.ocat = ((post.categories == null) || (post.categories.size() == 0)) ? "1": "1";
		ret.title = post.title;
		ret.brief = post.brief;
		ret.message = post.embed;
		ret.asite = post.duration;
		ret.files = post.images;
		return ret;
	}
}
