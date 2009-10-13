package migool.grab.host.redtube;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import migool.util.IOUtil;

/**
 * 
 * @author Denis Migol
 *
 */
public final class RedtubeUtil {
	private RedtubeUtil() {
	}

	/**
	 * 
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static final String getPage(HttpClient client, URL url) throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url.toURI());
		HttpResponse response = client.execute(get);
		String page = IOUtil.toString(response.getEntity().getContent());
		// TODO fix if page contain "ENTER" button
		return page;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static final boolean isId(String url) {
		return Pattern.compile("http://www\\.redtube\\.com/[0-9]+").matcher(url).matches();
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isPage(String url) {
		return Pattern.compile("http://www\\.redtube\\.com/(\\?page=[0-9]+){0,1}").matcher(url).matches();
	}

	/**
	 * 
	 * @param s
	 * @param l
	 * @return
	 */
	public static String appendPrefixWithZeros(String s, int l) {
		l -= s.length();
		for (int i = 0; i < l; i++) {
			s = "0" + s;
		}
		return s;
	}
}
