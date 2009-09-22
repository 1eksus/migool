package migool.http.methods;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author Denis Migol
 *
 */
public class HttpPost extends HttpMethod {
	public static final String METHOD_NAME = "POST";
	
	public HttpPost(URL url) {
		super(url);
	}
	
	public HttpPost(String url) throws MalformedURLException {
		super(url);
	}
	
	public String getName() {
		return METHOD_NAME;
	}
}
