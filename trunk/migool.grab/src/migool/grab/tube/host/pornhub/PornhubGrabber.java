package migool.grab.tube.host.pornhub;

import static migool.util.HtmlParserUtil.*;

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
	public static final String DURATION_REGEX = "[\\d]{1,3}\\:[\\d]{2}";
	
	private static final String DURATION = "duration";

	private static final NodeFilter VIDEOBLOCK_FILTER = new AndFilter(new TagNameFilter(LI), new AndFilter(
			new TagNameFilter(DIV), new HasAttributeFilter(CLASS, "wrap")));
	//private static final NodeFilter DURATION_FILTER = new AndFilter(new TagNameFilter(VAR), new HasAttributeFilter(CLASS, DURATION));
	private static final NodeFilter DURATION_FILTER = new HasAttributeFilter(CLASS, DURATION);

	/* fields */
	private final HttpClientWrapper httpClient;

	public PornhubGrabber() {
		httpClient = new HttpClientWrapper();
	}

	@Override
	public boolean isUrl(final String url) {
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
	private boolean isVideoBullet(NodeList children) {
		final NodeList alist = children.extractAllNodesThatMatch(new TagNameFilter("a"), true);
		if (alist != null) {
			final int size = alist.size();
			for (int i = 0; i < size; i++) {
				if (isUrl(((LinkTag) alist.elementAt(i)).getLink())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	private String getDuration(NodeList children) {
		final NodeList nl = children.extractAllNodesThatMatch(DURATION_FILTER, true);
		if (nl != null && nl.size() >= 1) {
			return RegexUtil.getMatch(nl.elementAt(0).getText(), DURATION_REGEX);
		}
		return null;
	}

	@Override
	public ITubeGrab[] grabPageUrl(String url) throws ClientProtocolException, IOException, ParserException {
		final String page = httpClient.requestToString(new HttpGet(url));
		final NodeList nl = (new Parser(page)).parse(VIDEOBLOCK_FILTER);
		int size = nl.size();
		for (int i = 0; i < size; i++) {
			final NodeList children = nl.elementAt(i).getChildren();
			if (isVideoBullet(children)) {
				// TODO
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
