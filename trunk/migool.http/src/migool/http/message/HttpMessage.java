package migool.http.message;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import migool.http.methods.HttpMethod;

/**
 * 
 * @author Denis Migol
 *
 */
public abstract class HttpMessage {

	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CONTENT_LENGTH = "Content-Length";
	
	public static final String CHARSET_EQ = "charset=";

	// fields:
	
	protected HttpMethod method;
	protected String startLine;
	protected Map<String, List<String>> headers;
	protected Charset charset = Charset.defaultCharset();
	protected HttpBody body;
	
	/**
	 * The constructor.
	 */
	public HttpMessage(HttpMethod method) {
		this.method = method;
		this.headers = new HashMap<String, List<String>>();
		this.body = new HttpBody();
	}

	public String getStartLine() {
		return startLine;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setBody(HttpBody body) {
		this.body = body;
	}

	public HttpBody getBody() {
		return body;
	}
	
	protected static Charset find(String contentType) {
		String name = "";
		int index = contentType.indexOf(CHARSET_EQ);
		if (index >= 0) {
			name = contentType.substring(index + CHARSET_EQ.length(), contentType.length());
		}
		try {
			return Charset.forName(name);
		} catch (Exception e) {
			return Charset.defaultCharset();
		}
	}

	public abstract void process(HttpURLConnection connection) throws IOException;
}
