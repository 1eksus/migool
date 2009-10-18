import static pornohouse.net.ru.PornohouseUtil.*;

import java.util.List;

import migool.grab.host.redtube.RedtubeGrab;
import migool.grab.host.redtube.RedtubeGrabber;
import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.http.client.HttpClientFactory;
import migool.poster.cms.ucoz.UcozPoster;
import migool.util.ProxyUtil;

import static migool.util.ProxyUtil.*;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;

import pornohouse.net.ru.PornohousePost;

/**
 * 
 * @author Denis Migol
 *
 */
public class Main {
	public static final Config CONFIG = new Config(); 

	private static final void printUsage() {
		System.out.println("Usage: " + Main.class.getName() + " username password url [proxy [host]]");
	}

	private static final void setProxy(final Proxy proxy) {
		System.out.println("using " + proxy);
		HttpClientFactory.setDefault(new HttpClientFactory() {
			@Override
			public HttpClient newHttpClient() {
				final HttpClient client = new DefaultHttpClient();
				final HttpHost hcProxyHost = new HttpHost(proxy.getHost(), proxy.getPort());
				client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, hcProxyHost);
				return client;
			}
		});
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			printUsage();
		}
		CONFIG.username = args[0];
		CONFIG.password = args[1];
		CONFIG.url = args[2];
		if (args.length > 3) {
			setProxy(ProxyUtil.parse(args[3]));
			if (args.length > 4) {
				CONFIG.host = args[4];
			}
		}
		
		System.out.println(CONFIG);

		RedtubeGrabber grabber = new RedtubeGrabber(CONFIG.url);
		List<RedtubeGrab> grabs = grabber.grab();
		PornohousePost post = null;
		if (grabs.size() > 0) {
			//UcozPoster poster = new UcozPoster("pornohouse.net.ru");
			UcozPoster poster = new UcozPoster(CONFIG.host);
			LoginResponse login = poster.login(new LoginPassword(CONFIG.username, CONFIG.password));
			if (login.getCode() == LoginResponse.OK) {
				//for (RedtubeGrab grab : grabs) {
				int size = grabs.size();
				for (int i = size - 1; i >= 0; i--) {
					RedtubeGrab grab = grabs.get(i);
					System.out.println(grab);
					post = toPornohousePost(grab);
					System.out.println(post);
					System.out.println(poster.post(toPublUcozPost(post)).getCode() + "\n");
				}
			} else {
				System.out.println("Can't login into " + CONFIG.host);
			}
		} else {
			System.out.println("none posted");
		}
	}
}
