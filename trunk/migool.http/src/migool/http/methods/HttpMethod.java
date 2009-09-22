package migool.http.methods;

import java.net.MalformedURLException;
import java.net.URL;

import migool.http.message.HttpRequest;
import migool.http.message.HttpResponse;

/**
 * 
 * @author Denis Migol
 *
 */
public abstract class HttpMethod {
	protected URL url;
	protected HttpRequest request;
	protected HttpResponse response;
	
	public HttpMethod(URL url) {
		this.url = url;
		request = new HttpRequest(this);
		response = new HttpResponse(this);
	}
	
	public HttpMethod(String url) throws MalformedURLException {
		this(new URL((url.startsWith("http://")) ? url : "http://" + url));
	}
	
	public URL getUrl() {
		return this.url;
	}
	
	public void setRequest(HttpRequest request) {
		this.request = request;
	}
	
	public HttpRequest getRequest() {
		return request;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}
	
	public HttpResponse getResponse() {
		return response;
	}
	
	public abstract String getName();
}
