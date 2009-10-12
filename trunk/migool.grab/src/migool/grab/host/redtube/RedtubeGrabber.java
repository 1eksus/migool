package migool.grab.host.redtube;

import org.apache.http.client.HttpClient;

import migool.grab.IGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RedtubeGrabber implements IGrabber {

	public static final String HOST = "redtube.com";

	private HttpClient client;

	public RedtubeGrabber() {

	}

	@Override
	public String getHost() {
		return HOST;
	}
}
