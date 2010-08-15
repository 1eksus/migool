package migool.http.client;

import org.apache.http.client.HttpClient;

/**
 * 
 * @author Denis Migol
 * 
 */
public class HttpClientableBase implements IHttpClientable {

	protected final HttpClientWrapper client = new HttpClientWrapper();

	public HttpClient getHttpClient() {
		return client.getHttpClient();
	}

	public void setHttpClient(final HttpClient httpClient) {
		client.setHttpClient(httpClient);
	}

	public HttpClientWrapper getHttpClientWrapper() {
		return client;
	}
}
