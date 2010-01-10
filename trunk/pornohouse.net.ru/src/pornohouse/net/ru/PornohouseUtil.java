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

import migool.entity.FileEntity;
import migool.grab.tube.TubeGrab;
import migool.http.client.HttpClientFactory;
import migool.post.cms.ucoz.post.PublUcozPost;
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

	private static final List<FileEntity> toListImage(List<String> urls) {
		ArrayList<FileEntity> ret = new ArrayList<FileEntity>(urls.size());
		HttpClient client = HttpClientFactory.get().newHttpClient();
		HttpGet get = null;
		HttpResponse response = null;
		FileEntity img = null;
		for (String url : urls) {
			get = new HttpGet(url);
			try {
				response = client.execute(get);
				img = new FileEntity();
				//img.bytes = IOUtil.toByteArray(response.getEntity().getContent());
				img.setBytes(toByteArray(response.getEntity().getContent()));
				img.setFileName((new File(url)).getName());
				ret.add(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static final PornohousePost toPornohousePost(TubeGrab grab) {
		PornohousePost ret = new PornohousePost();
		ret.url = grab.getIdUrl();
		ret.title = grab.getTitle();
		// TODO categories
		// TODO brief ?
		ret.embed = grab.getEmbed();
		ret.duration = grab.getDuration();
		final String[] thumbs = grab.getThumbUrls();
		ArrayList<String> urls = new ArrayList<String>(thumbs.length + 1);
		urls.add(grab.getThumbUrl());
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
