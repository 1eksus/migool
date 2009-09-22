package migool.http.message;

import java.io.IOException;
import java.net.HttpURLConnection;

import migool.http.methods.HttpMethod;

/**
 * 
 * @author Denis Migol
 *
 */
public class HttpResponse extends HttpMessage {

	public HttpResponse(HttpMethod method) {
		super(method);
	}

	public void process(HttpURLConnection connection) throws IOException {
		headers = connection.getHeaderFields();
		for (String key : headers.keySet()) {
			System.out.println(key);
			System.out.println("\t" + headers.get(key));
			if (CONTENT_TYPE.equalsIgnoreCase(key)) {
				charset = find(headers.get(key).get(0));
				body.setCharset(charset);
			}
		}
		body.read(connection.getInputStream());
	}

}
