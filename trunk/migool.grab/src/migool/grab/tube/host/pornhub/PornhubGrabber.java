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
	
	static final int MIN_NUMBER_PAGE = 1;

	public static final String URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/view\\_video\\.php\\?viewkey\\=([\\d]{8,10}|[\\w]+)";
	public static final String PAGE_URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/video\\?o\\=mr\\&page\\=[\\d]+";
	public static final String PAGE_URL_PREFIX = "http://www.pornhub.com/video?o=mr&page=";
	public static final String DURATION_REGEX = "[\\d]{1,3}\\:[\\d]{2}";

	public static final String START_THUMB_CHANGE_REGEX = "(?<=onmouseover\\=\\\"startThumbChange\\()[\\w\\,\\ \\:\\/\\'\\.]+(?=\\)\\;\\\")";
	public static final String START_THUMB_CHANGE_COUNT_REGEX = "(?<= )[\\d]+(?=\\,)";
	public static final String START_THUMB_CHANGE_PREFIX_REGEX = "(?<=\\ \\')[\\w\\,\\:\\/\\.]+(?=\\')";

	private static final String DURATION = "duration";

	private static final NodeFilter VIDEOBLOCK_FILTER = new AndFilter(new TagNameFilter(LI), new HasChildFilter(
			new AndFilter(new TagNameFilter(DIV), new HasAttributeFilter(CLASS, "wrap")), true));
	// private static final NodeFilter DURATION_FILTER = new AndFilter(new
	// TagNameFilter(VAR), new HasAttributeFilter(CLASS, DURATION));
	private static final NodeFilter DURATION_FILTER = new HasAttributeFilter(CLASS, DURATION);
//	private static final NodeFilter EMBED_FILTER = new AndFilter(TEXTAREA_FILTER, new HasChildFilter(OBJECT_FILTER,
//			true));

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
					return link;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	private String grabIdFromPage(ImageTag img) {
		String ret = null;
		if (EmptyChecker.isNotNullOrEmpty(ret = img.getAttribute(ID))) {
			return ret;
		}
		return ret;
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
		return img.getImageURL();
	}

	/**
	 * 
	 * @param children
	 * @return
	 */
	private String[] grabThumbUrlsFromPage(ImageTag img) {
		ArrayList<String> ret = new ArrayList<String>(16);
		final String thumbText = RegexUtil.getMatch(img.getText(), START_THUMB_CHANGE_REGEX);
		final int count = Integer.parseInt(RegexUtil.getMatch(thumbText, START_THUMB_CHANGE_COUNT_REGEX));
		final String prefix = RegexUtil.getMatch(thumbText, START_THUMB_CHANGE_PREFIX_REGEX);
		for (int i = 0; i < count; i++) {
			ret.add(prefix + "small" + (i + 1) + ".jpg");
		}
		return ret.toArray(new String[ret.size()]);
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
				// final String idPage = httpClient.requestToString(new
				// HttpGet(idUrl));
				final String id = grabIdFromPage(img);

				final ITubeGrabBuilder b = new TubeGrabBuilder().setId(id).setIdUrl(idUrl).setTitle(
						grabTitleFromPage(children)).setThumbUrl(grabThumbUrlFromPage(img)).setThumbUrls(
						grabThumbUrlsFromPage(img)).setDuration(grabDuration(children)).setEmbed(grabEmbedFromId(id));

				ret.add(b.build());
			}
		}
		return ret.toArray(new ITubeGrab[ret.size()]);
	}

	/**
	 * 
	 * @return
	 * @throws ParserException
	 */
	private String grabEmbedFromId(String id) throws ParserException {
		// System.out.println(((TextareaTag)(new
		// Parser(idPage)).parse(EMBED_FILTER).elementAt(0)).getStringText());
		return String
				.format(
						"<object type=\"application/x-shockwave-flash\" "
								+ "data=\"http://cdn-www.pornhub.com/flash/embed_player_v1.3.swf\" width=\"608\" height=\"476\">"
								+ "<param name=\"movie\" value=\"http://cdn-www.pornhub.com/flash/embed_player_v1.3.swf\" />"
								+ "<param name=\"bgColor\" value=\"#000000\" />"
								+ "<param name=\"allowfullscreen\" value=\"true\" />"
								+ "<param name=\"allowScriptAccess\" value=\"always\" />"
								+ "<param name=\"FlashVars\" value=\"options=http://www.pornhub.com/embed_player.php?id=%1$s\"/></object>",
						id);
	}

	@Override
	public ITubeGrab grabIdUrl(String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPageUrl(int number) {
		if (number < MIN_NUMBER_PAGE) {
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
