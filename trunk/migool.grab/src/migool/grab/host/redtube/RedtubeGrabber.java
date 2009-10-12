package migool.grab.host.redtube;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
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

	private String videosPage;
	private String videoPage;
	private String embedPage;

	private List<RedtubeGrab> grabs = new ArrayList<RedtubeGrab>();

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
		String urlString = url.toString();
		System.out.println(urlString);
	}

	private void enter() {
		
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
