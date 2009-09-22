package migool.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.Arrays;

import migool.http.message.HttpBody;
import migool.http.message.HttpRequest;
import migool.http.methods.HttpGet;
import migool.http.methods.HttpMethod;
import migool.http.methods.HttpPost;

public class HttpClient {
	CookieManager cm = new CookieManager();

	public int execute(HttpMethod method) throws IOException, URISyntaxException {
		HttpURLConnection connection = (HttpURLConnection) method.getUrl().openConnection();

		// before connect
		
		cm.setCookies(connection);

		// connect

		method.getRequest().process(connection);
		method.getResponse().process(connection);

		// after connect

		cm.storeCookies(connection);

		return connection.getResponseCode();
	}

	/**
	 * Test.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();

		//String site = "http://hasard.ru";
		//String site = "http://hasard.ru/engine/modules/antibot.php";
		String site = "http://dle80";
		//String site = "http://lookszone.ru";
		String request = "login_name=zm1gol&login_password=Z3tCd2fw&login=submit";
//		 String site = "http://qiq.ru";
//		 String request = "login=zm1gol&pass=Z3tCd2fw&action=login&mem=1";

		HttpMethod post = new HttpPost(site);
		post.getRequest().setBody(new HttpBody(request));
		System.out.println(httpClient.execute(post));
		System.out.println(new String(post.getResponse().getBody().getBytes()));
		if (true) {
			return;
		}

		HttpMethod get = new HttpGet(site);
		get.getRequest().getHeaders().put(HttpRequest.REFERER, Arrays.asList(new String[]{"http://hasard.ru/index.php?do=register"}));
		System.out.println(httpClient.execute(get));
		System.out.println(get.getResponse().getBody());
		OutputStream os = new FileOutputStream("d:/3.jpg");
		os.write(get.getResponse().getBody().getBytes());
		os.close();
	}
}
