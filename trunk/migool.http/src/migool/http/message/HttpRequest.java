package migool.http.message;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import migool.http.methods.HttpGet;
import migool.http.methods.HttpMethod;

/**
 * 
 * @author Denis Migol
 *
 */
public class HttpRequest extends HttpMessage {
	
	public static final String REFERER = "Referer";
	
	public HttpRequest(HttpMethod method) {
		super(method);
	}
	
	protected void processHeaders(HttpURLConnection connection) {
		for (String key : headers.keySet()) {
			connection.setRequestProperty(key, headers.get(key).get(0));
		}
	}

	public void process(HttpURLConnection connection) throws IOException {
		String methodName = method.getName();
		if (!HttpGet.METHOD_NAME.equals(methodName)) {
			connection.setRequestMethod(methodName);
		}
		if (headers == null) {
			headers = new HashMap<String, List<String>>();
		}
		int bodyLength = -1;
		boolean isBodyRequered = (body != null) && ((bodyLength = body.length()) > 0);
		if (isBodyRequered) {
			headers.put(CONTENT_LENGTH, Arrays.asList(new String[]{bodyLength + ""}));
		}
		processHeaders(connection);
		if (isBodyRequered) {
			connection.setDoOutput(true);
			body.write(connection.getOutputStream());
		}
	}

}
