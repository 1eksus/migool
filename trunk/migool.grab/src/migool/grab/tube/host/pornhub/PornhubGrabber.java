package migool.grab.tube.host.pornhub;

import static migool.util.HtmlParserUtil.*;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import migool.grab.tube.ITubeGrab;
import migool.grab.tube.ITubeGrabBuilder;
import migool.grab.tube.ITubeGrabber;
import migool.grab.tube.TubeGrabBuilder;
import migool.grab.tube.TubeGrabberBase;
import migool.http.client.HttpClientFactory;
import migool.http.client.HttpClientWrapper;
import migool.util.EmptyChecker;
import migool.util.RegexUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public class PornhubGrabber extends TubeGrabberBase {

	public static final String HOST = "www.pornhub.com";
	public static final String URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/view\\_video\\.php\\?viewkey\\=([\\d]{8,10}|[\\w]+)";
	public static final String PAGE_URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/video\\?o\\=mr\\&page\\=[\\d]+";
	public static final String PAGE_URL_PREFIX = "http://www.pornhub.com/video?o=mr&page=";
	public static final String DURATION_REGEX = "[\\d]{1,3}\\:[\\d]{2}";

	private static final String DURATION = "duration";

	private static final NodeFilter VIDEOBLOCK_FILTER = new AndFilter(new TagNameFilter(LI), new HasChildFilter(
			new AndFilter(new TagNameFilter(DIV), new HasAttributeFilter(CLASS, "wrap"))));
	// private static final NodeFilter DURATION_FILTER = new AndFilter(new
	// TagNameFilter(VAR), new HasAttributeFilter(CLASS, DURATION));
	private static final NodeFilter DURATION_FILTER = new HasAttributeFilter(CLASS, DURATION);

	/* fields */
	private final HttpClientWrapper httpClient;

	public PornhubGrabber() {
		httpClient = new HttpClientWrapper();
	}

	@Override
	public boolean isIdUrl(final String url) {
		return RegexUtil.isMatch(URL_REGEX, url);
	}

	@Override
	public boolean isPageUrl(final String url) {
		return RegexUtil.isMatch(PAGE_URL_REGEX, url);
	}

	@Override
	public String getHost() {
		return HOST;
	}

	/**
	 * 
	 * @param children
	 * @return
	 */
	private String grabIdUrlFromPage(NodeList children) {
		final NodeList alist = children.extractAllNodesThatMatch(A_FILTER, true);
		if (alist != null) {
			final int size = alist.size();
			String link = null;
			for (int i = 0; i < size; i++) {
				link = ((LinkTag) alist.elementAt(i)).getLink();
				if (isIdUrl(link)) {
					// return RegexUtil.getMatch(link, URL_REGEX);
					return link;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param children
	 * @return
	 */
	private String grabTitleFromPage(NodeList children) {
		final NodeList alist = children.extractAllNodesThatMatch(A_FILTER, true);
		if (alist != null) {
			final int size = alist.size();
			String title = null;
			for (int i = 0; i < size; i++) {
				if (EmptyChecker.isNotNullOrEmpty(title = ((LinkTag) alist.elementAt(i)).getAttribute(TITLE))) {
					return title;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param children
	 * @return
	 */
	private String grabThumbUrlFromPage(ImageTag img) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param children
	 * @return
	 */
	private String[] grabThumbUrlsFromPage(NodeList children) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param children
	 * @return
	 */
	private String grabDuration(NodeList children) {
		final NodeList nl = children.extractAllNodesThatMatch(DURATION_FILTER, true);
		if (nl != null && nl.size() >= 1) {
			return RegexUtil.getMatch(nl.elementAt(0).getParent().toHtml(), DURATION_REGEX);
		}
		return null;
	}

	@Override
	public ITubeGrab[] grabPageUrl(String url) throws ClientProtocolException, IOException, ParserException {
		final ArrayList<ITubeGrab> ret = new ArrayList<ITubeGrab>();
		final String page = httpClient.requestToString(new HttpGet(url));
		final NodeList nl = (new Parser(page)).parse(VIDEOBLOCK_FILTER);
		int size = nl.size();
		for (int i = 0; i < size; i++) {
			final NodeList children = nl.elementAt(i).getChildren();
			String idUrl = null;
			if (EmptyChecker.isNotNullOrEmpty(idUrl = grabIdUrlFromPage(children))) {
				final LinkTag link = (LinkTag) children.extractAllNodesThatMatch(A_IMG_FILTER, true).elementAt(0);
				final ImageTag img = (ImageTag) link.getChildren().extractAllNodesThatMatch(IMG_FILTER).elementAt(0);

				final ITubeGrabBuilder b = new TubeGrabBuilder().setIdUrl(idUrl).setTitle(grabTitleFromPage(children))
						.setThumbUrl(grabThumbUrlFromPage(img)).setThumbUrls(grabThumbUrlsFromPage(children))
						.setDuration(grabDuration(children));

				ret.add(b.build());
			}
		}
		return ret.toArray(new ITubeGrab[ret.size()]);
	}

	@Override
	public ITubeGrab grabUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageUrl(int number) {
		if (number < 1) {
			throw new IllegalArgumentException(number + "");
		}
		return PAGE_URL_PREFIX + number;
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		HttpClientFactory.setDefault(new HttpClientFactory() {

			@Override
			public HttpClient newHttpClient() {
				final HttpClient client = new DefaultHttpClient();
				final HttpHost hcProxyHost = new HttpHost("127.0.0.1", 8081);
				client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, hcProxyHost);
				return client;
			}
		});
		ITubeGrabber grabber = new PornhubGrabber();
		ITubeGrab[] grabs = grabber.grab("http://www.pornhub.com/video?o=mr&page=1");
		if (grabs != null) {
			for (int i = 0; i < grabs.length; i++) {
				System.out.println(grabs[i]);
			}
			System.out.println(grabs.length);
		}
	}
}
