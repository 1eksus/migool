package migool.http.client;

import org.apache.http.client.HttpClient;

/**
 * 
 * @author Denis Migol
 *
 */
public class HttpClientableBase implements IHttpClientable {

	protected HttpClient httpClient;

	@Override
	public HttpClient getHttpClient() {
		return httpClient;
	}

	@Override
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
}
