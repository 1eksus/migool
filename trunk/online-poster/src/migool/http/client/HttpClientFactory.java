package migool.http.client;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.GAEConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

/**
 * 
 * @author Denis Migol
 *
 */
public abstract class HttpClientFactory {

	public abstract HttpClient newHttpClient();
	
	public static final HttpClientFactory newInstance() {
		return new HttpClientFactory() {
			public HttpClient newHttpClient() {
				return new DefaultHttpClient(new GAEConnectionManager(), new BasicHttpParams());
			}
		};
	}
}
