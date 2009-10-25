package migool.http.client;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 
 * @author Denis Migol
 * 
 */
public abstract class HttpClientFactory {

	public abstract HttpClient newHttpClient();

	protected static HttpClientFactory defFactory = new HttpClientFactory() {
		public HttpClient newHttpClient() {
			return new DefaultHttpClient();
		}
	};

	public static final HttpClientFactory get() {
		return defFactory;
	}
	
	public static void setDefault(HttpClientFactory defFactory) {
		HttpClientFactory.defFactory = defFactory;
	}
}
