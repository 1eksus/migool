package migool.http.client;

import java.io.IOException;

import migool.util.IOUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * 
 * @author Denis Migol
 *
 */
public final class HttpClientWrapper {
	private final HttpClient client;

	public HttpClientWrapper(HttpClient client) {
		this.client = client;
	}

	public String requestToString(HttpUriRequest request) throws ClientProtocolException, IOException {
		HttpResponse response = client.execute(request);
		return IOUtil.toString(response.getEntity().getContent());
	}
}
