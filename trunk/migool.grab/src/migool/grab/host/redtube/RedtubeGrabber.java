package migool.grab.host.redtube;

import static migool.grab.host.redtube.RedtubeUtil.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;

import migool.grab.IGrabber;
import migool.http.client.HttpClientFactory;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RedtubeGrabber implements IGrabber {

	public static final String HOST = "redtube.com";

	private HttpClient client = HttpClientFactory.newInstance().newHttpClient();

	private URL url;

	/**
	 * @throws MalformedURLException
	 * 
	 */
	public RedtubeGrabber(String url) throws MalformedURLException {
		this(new URL(url));
	}

	/**
	 * 
	 * @param url
	 */
	public RedtubeGrabber(URL url) {
		this.url = url;
	}

	/**
	 * 
	 * @return
	 */
	private static final RedtubeGrab grabId(HttpClient client, URL url) {
		RedtubeGrab ret = new RedtubeGrab();
		// TODO
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	private static final List<RedtubeGrab> grabPage(HttpClient client, URL url) {
		ArrayList<RedtubeGrab> ret = new ArrayList<RedtubeGrab>();
		return ret;
	}

	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws ClientProtocolException 
	 */
	public List<RedtubeGrab> grab() throws ClientProtocolException, URISyntaxException, IOException {
		String url = this.url.toString();
		if (isPage(url)) {
			return grabPage(client, this.url);
		} else if (isId(url)) {
			return Arrays.asList(new RedtubeGrab[] {grabId(client, this.url)});
		}
		return null;
	}

	@Override
	public String getHost() {
		return HOST;
	}

	public static void main(String[] args) throws Exception {
		HttpClientFactory.setDefault(new HttpClientFactory() {
			@Override
			public HttpClient newHttpClient() {
				final HttpClient client = new DefaultHttpClient();
				final HttpHost hcProxyHost = new HttpHost("127.0.0.1", 8081);
				client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, hcProxyHost);
				return client;
			}
		});
		RedtubeGrabber grabber = new RedtubeGrabber("http://www.redtube.com/?page=757");
		
	}
}
