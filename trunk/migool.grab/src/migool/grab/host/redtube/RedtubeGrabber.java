package migool.grab.host.redtube;

import static migool.grab.host.redtube.RedtubeUtil.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

import migool.grab.IGrabber;
import migool.http.client.HttpClientFactory;
import migool.util.Regex;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RedtubeGrabber implements IGrabber {

	public static final String HOST = "redtube.com";

	private HttpClient client = HttpClientFactory.newInstance().newHttpClient();

	private URL url;

	/**
	 * @throws MalformedURLException
	 * 
	 */
	public RedtubeGrabber(String url) throws MalformedURLException {
		this(new URL(url));
	}

	/**
	 * 
	 * @param url
	 */
	public RedtubeGrabber(URL url) {
		this.url = url;
	}

	/**
	 * 
	 * @return
	 */
	private static final RedtubeGrab grabId(HttpClient client, URL url) {
		RedtubeGrab ret = new RedtubeGrab();
		// TODO
		return ret;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private static final String getEmbed(int id) {
		// TODO get through the url 
		return "<object height=\"344\" width=\"434\">"
				+ "<param name=\"movie\" value=\"http://embed.redtube.com/player/\">"
				+ "<param name=\"FlashVars\" value=\"id=" + id + "&style=redtube\">" + "<embed "
				+ "src=\"http://embed.redtube.com/player/?id=" + id + "&style=redtube\""
				+ "pluginspage=\"http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\""
				+ "type=\"application/x-shockwave-flash\" height=\"344\" width=\"434\" />" + "</object>";
	}

	/**
	 * 
	 * @param link
	 * @return
	 */
	private static final int extractId(String link) {
		return Integer.parseInt(new Regex(link, "[\\d]+$").getMatches()[0][0]);
	}
	
	private static final Integer[] parse10(String pic) {
		String[] strings = (new Regex(pic, "[01]{1}[,\\)]")).getMatchesAsArray();
		ArrayList<Integer> nums = new ArrayList<Integer>(strings.length);
		for (String string : strings) {
			nums.add(new Integer(Integer.parseInt(string.substring(0, string.length() - 1))));
		}
		return nums.toArray(new Integer[nums.size()]);
	}
	
	private static final TreeMap<Integer, Integer[]> parsePics(String script) {
		String regexPics = "pics\\[\\'[\\d]{7}\\'\\][\t ]*\\=[\t ]*new[\\ ]*Array\\([01\\,\t ]*\\);";
		TreeMap<Integer, Integer[]> ret = new TreeMap<Integer, Integer[]>();
		String[] strings = (new Regex(script, regexPics)).getMatchesAsArray();
		int id;
		for (String pic : strings) {
			id = Integer.parseInt((new Regex(pic, "[\\d]{7}")).getMatches()[0][0]);
			ret.put(id, parse10(pic));
		}
		return ret;
	}

	/**
	 * 
	 * @param link
	 * @return
	 */
	private static final String getThumbPrefix(String link) {
		return link.substring(0, link.lastIndexOf(".jpg") - 3);
	}

	/**
	 * 
	 * @return
	 */
	private static final String getThumbLink(String prefix, int i) {
		return prefix + appendPrefixWithZeros(i + "", 3) + ".jpg";
	}
	
	private static final String[] getThumbs(String linkThumb, Integer[] nums) {
		ArrayList<String> ret = new ArrayList<String>();
		String prefix = getThumbPrefix(linkThumb);
		int length = nums.length;
		int num = 0;
		for (int i = 0; i < length; i++) {
			num = nums[i];
			if (num != 0) {
				ret.add(getThumbLink(prefix, i + 1));
			}
		}
		return ret.toArray(new String[ret.size()]);
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 */
	private static final List<RedtubeGrab> grabPage(HttpClient client, URL url) throws ClientProtocolException,
			URISyntaxException, IOException {
		ArrayList<RedtubeGrab> ret = new ArrayList<RedtubeGrab>();
		String pageIds = getPage(client, url);
		// System.out.println(pageIds);
		try {
			Parser parser = new Parser(pageIds);
			NodeList videoThumbs = parser.parse(new AndFilter(new TagNameFilter("ul"),
					new HasAttributeFilter("class", "videoThumbs")));
			NodeList lis = videoThumbs.extractAllNodesThatMatch(new TagNameFilter("li"), true);
			ret.ensureCapacity(lis.size());

			parser.reset();
			String script = ((ScriptTag)parser.parse(new AndFilter(new TagNameFilter("script"), new HasParentFilter(new HasAttributeFilter("class", "videosTable")))).elementAt(0)).getChildrenHTML();
			TreeMap<Integer, Integer[]> pics = parsePics(script);

			Node li = null;
			RedtubeGrab grab = null;
			int id = -1;
			LinkTag a = null;
			for (int i = 0; i < lis.size(); i++) {
				li = lis.elementAt(i);
				a = (LinkTag)li.getChildren().extractAllNodesThatMatch(new TagNameFilter("a"), true).elementAt(0);
				id = extractId(a.extractLink());
				if (id > 0) {
					grab = new RedtubeGrab();
					grab.share = "http://www.redtube.com/" + id;
					grab.title = a.getAttribute("title");
					grab.thumb = ((ImageTag)a.getChildren().extractAllNodesThatMatch(new TagNameFilter("img"), true).elementAt(0)).getImageURL();
					grab.thumbs = getThumbs(grab.thumb, pics.get(id));
					grab.duration = ((Span)li.getChildren().extractAllNodesThatMatch(new AndFilter(new TagNameFilter("span"), new HasAttributeFilter("class", "d")), true).elementAt(0)).getStringText();
					grab.embed = getEmbed(id);
					ret.add(grab);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 */
	public List<RedtubeGrab> grab() throws ClientProtocolException, URISyntaxException, IOException {
		String url = this.url.toString();
		if (isPage(url)) {
			return grabPage(client, this.url);
		} else if (isId(url)) {
			return Arrays.asList(new RedtubeGrab[] { grabId(client, this.url) });
		}
		return null;
	}

	@Override
	public String getHost() {
		return HOST;
	}

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
		RedtubeGrabber grabber = new RedtubeGrabber("http://www.redtube.com/?page=758");
		grabber.grab();
	}
}
