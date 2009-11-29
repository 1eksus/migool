package migool.grab.tube.host.pornhub;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import migool.grab.tube.ITubeGrab;
import migool.grab.tube.TubeGrabberBase;
import migool.http.client.HttpClientWrapper;
import migool.util.RegexUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public class PornhubGrabber extends TubeGrabberBase {

	public static final String HOST = "www.pornhub.com";
	public static final String URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/view\\_video\\.php\\?viewkey\\=[\\d]{9}";
	public static final String PAGE_URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/video\\?o\\=mr\\&page\\=[\\d]+";
	public static final String PAGE_URL_PREFIX = "http://www.pornhub.com/video?o=mr&page=";

	private static final NodeFilter VIDEOS_PAGE_FILTER = new AndFilter(new TagNameFilter("li"), new AndFilter(
			new TagNameFilter("div"), new HasAttributeFilter("class", "wrap")));

	/* fields */
	private final HttpClientWrapper httpClient;

	public PornhubGrabber() {
		httpClient = new HttpClientWrapper();
	}

	public boolean isUrl(String url) {
		return RegexUtil.isMatch(URL_REGEX, url);
	}

	public boolean isPageUrl(String url) {
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
	private boolean isVideoBullet(NodeList children) {
		NodeList alist = children.extractAllNodesThatMatch(new TagNameFilter("a"), true);
		if (alist != null) {
			int size = alist.size();
			for (int i = 0; i < size; i++) {
				if (isUrl(((LinkTag) alist.elementAt(i)).getLink())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public ITubeGrab[] grabPageUrl(String url) throws ClientProtocolException, IOException, ParserException {
		final String page = httpClient.requestToString(new HttpGet(url));
		final NodeList nl = (new Parser(page)).parse(VIDEOS_PAGE_FILTER);
		int size = nl.size();
		for (int i = 0; i < size; i++) {
			final NodeList children = nl.elementAt(i).getChildren();
			if (isVideoBullet(children)) {

			}
		}
		// TODO Auto-generated method stub
		return null;
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
}
