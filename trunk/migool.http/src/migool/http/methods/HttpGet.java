package migool.http.methods;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Denis Migol
 *
 */
public class HttpGet extends HttpMethod {
	public static final String METHOD_NAME = "GET";
	
	public HttpGet(URL url) {
		super(url);
	}
	
	public HttpGet(String url) throws MalformedURLException {
		super(url);
	}
	
	public String getName() {
		return METHOD_NAME;
	}
}
