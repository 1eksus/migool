package migool.grab.host.redtube;

import org.apache.http.client.HttpClient;

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

	public RedtubeGrabber() {
		
	}
	
	private void enter() {
		
	}

	@Override
	public String getHost() {
		return HOST;
	}
}
