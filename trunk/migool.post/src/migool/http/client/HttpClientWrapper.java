package migool.http.client;

import static migool.http.client.HttpClientUtil.*;

import java.io.IOException;

import migool.util.IOUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class HttpClientWrapper {
	private final HttpClient client;
	private String charset;
	private boolean isFirstRequest = true;

	/**
	 * 
	 * @param client
	 */
	public HttpClientWrapper(HttpClient client) {
		this.client = client;
	}

	/**
	 * 
	 * @return
	 */
	public HttpClient getClient() {
		return client;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String requestToString(HttpUriRequest request) throws ClientProtocolException, IOException {
		HttpResponse response = client.execute(request);
		if (isFirstRequest) {
			charset = parseCharset(response.getEntity().getContentType().getValue());
			isFirstRequest = false;
		}
		return IOUtil.toString(response.getEntity().getContent());
	}

	/**
	 * 
	 * @param request
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void requestToVoid(HttpUriRequest request) throws ClientProtocolException, IOException {
		HttpResponse response = client.execute(request);
		response.getEntity().getContent().close();
	}

	/**
	 * 
	 * @param uri
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getToString(String uri) throws ClientProtocolException, IOException {
		return requestToString(new HttpGet(uri));
	}

	/**
	 * 
	 * @return
	 */
	public String getCharset() {
		return charset;
	}
}
