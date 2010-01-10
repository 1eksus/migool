import static pornohouse.net.ru.PornohouseUtil.*;

import java.util.List;

import migool.auth.Login;
import migool.grab.tube.TubeGrab;
import migool.http.client.HttpClientFactory;
import migool.post.cms.ucoz.UcozPoster;
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

//		ITubeGrabber grabber = new Redtube
		List<TubeGrab> grabs = null;//grabber.grab();
		PornohousePost post = null;
		if (grabs.size() > 0) {
			//UcozPoster poster = new UcozPoster("pornohouse.net.ru");
			UcozPoster poster = new UcozPoster(CONFIG.host);
			poster.login(new Login(CONFIG.username, CONFIG.password));
				//for (RedtubeGrab grab : grabs) {
				int size = grabs.size();
				for (int i = size - 1; i >= 0; i--) {
					TubeGrab grab = grabs.get(i);
					System.out.println(grab);
					post = toPornohousePost(grab);
					System.out.println(post);
					System.out.println(poster.post(toPublUcozPost(post)) + "\n");
				}
		} else {
			System.out.println("none posted");
		}
	}
}
