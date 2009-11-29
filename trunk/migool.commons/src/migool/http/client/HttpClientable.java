package migool.http.client;

import org.apache.http.client.HttpClient;

/**
 * 
 * @author Denis Migol
 *
 */
public interface HttpClientable {
	/**
	 * 
	 * @param HttpClient
	 */
	void setHttpClient(HttpClient HttpClient);

	/**
	 * 
	 * @return
	 */
	HttpClient getHttpClient();
}
