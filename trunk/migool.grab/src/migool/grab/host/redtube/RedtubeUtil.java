package migool.grab.host.redtube;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import migool.util.IOUtil;
import migool.util.NetUtil;
import migool.util.Regex;
import migool.util.RegexUtil;

/**
 * 
 * @author Denis Migol
 *
 */
public final class RedtubeUtil {
	private RedtubeUtil() {
	}

	/**
	 * 
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static final String getPage(HttpClient client, URL url) throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url.toURI());
		HttpResponse response = client.execute(get);
		String page = IOUtil.toString(response.getEntity().getContent());
		// TODO fix if page contain "ENTER" button
		return page;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static final boolean isId(String url) {
		return Pattern.compile("http://www\\.redtube\\.com/[0-9]+").matcher(url).matches();
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isPage(String url) {
		return Pattern.compile("http://www\\.redtube\\.com/(\\?page=[0-9]+){0,1}").matcher(url).matches();
	}

//	/**
//	 * 
//	 * @param text
//	 * @return
//	 */
//	public static final String[] getIds(String text) {
//		if (RegexUtil.isMatch("http\\:\\/\\/(www\\.){0,1}redtube\\.com\\/\\?page=[0-9]+", text)) {
//			return getIds(NetUtil.newURL(text));
//		}
//		try {
//			String[][] urls = new Regex(text, "(?<=href=\\'\\/)[0-9]+(?=\\')").getMatches();
//			if (urls == null) {
//				return null;
//			}
//			List<String> ret = new ArrayList<String>(40);
//			for (int i = 0; i < urls.length; i++) {
//				for (int j = 0; j < urls[i].length; j++) {
//					String url = "http://www.redtube.com/" + urls[i][j];
//					if (!ret.contains(url)) {
//						ret.add(url);
//					}
//				}
//			}
//			return ret.toArray(new String[ret.size()]);
//		} catch (Exception e) {
//		}
//		return null;
//	}
	
	public static final String[] getIds(String page) {
		ArrayList<String> ret = new ArrayList<String>();
		return ret.toArray(new String[ret.size()]);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static final String[] getIds(URL url) {
		try {
			String page = NetUtil.getPage(url);
			return getIds(page);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String[] getDurations(String text) {
		if (RegexUtil.isMatch("http\\:\\/\\/(www\\.){0,1}redtube\\.com\\/\\?page=[0-9]+", text)) {
			return getDurations(NetUtil.newURL(text));
		}
		try {
			String[][] urls = new Regex(text, "[0-9]{2}\\:[0-9]{2}").getMatches();
			List<String> ret = new ArrayList<String>();
			if (urls == null) {
				return null;
			}
			for (int i = 0; i < urls.length; i++) {
				for (int j = 0; j < urls[i].length; j++) {
					//if (!ret.contains(urls[i][j])) {
						ret.add(urls[i][j]);
					//}
				}
			}
			return ret.toArray(new String[ret.size()]);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String[] getDurations(URL url) {
		try {
			String page = NetUtil.getPage(url);
			return getDurations(page);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @param n
	 * @return
	 */
	private static String getThumbsPrefix(String url, int n) {
		return getThumbsPrefix(url, n, "http://thumbs.redtube.com/_thumbs/");
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private static String getPathNr(String id) {
		String pathnr = "" + (int) Math.floor(Double.parseDouble(id) / 1000);
		int l = pathnr.length();
		for (int i = 1; i <= 7 - l; i++) {
			pathnr = "0" + pathnr;
		}
		return pathnr;
	}

	public static String appendPrefixWithZeros(String s, int l) {
		l -= s.length();
		for (int i = 0; i < l; i++) {
			s = "0" + s;
		}
		return s;
	}

	private static String updateId(String id) {
		int l = id.length();
		int i = 1;
		String s = id;
		while (i <= 7 - l) {
			s = "0" + s;
			++i;
		} // end while
		return s;
	}

	public static String getThumbsPrefix(String url, int n, String prefix) {
		String id = updateId(getId(url));
		String path = getPathNr(id) + "/" + id + "/" + id + "_" + appendPrefixWithZeros(n + "", 3) + ".jpg";
		if (!prefix.endsWith("/")) {
			prefix += "/";
		}
		return prefix + path;
	}
	
	private static String getId(String url) {
		if (!Pattern.compile("http://www\\.redtube\\.com/[0-9]+").matcher(url).matches()) {
			return "";
		}
		String[] split = Pattern.compile("http://www\\.redtube\\.com/").split(url);
		return (split.length > 0) ? split[1] : "";
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String[] getThumbsUrls(String url) {
		String[] ret = new String[16];
		for (int i = 0; i < 16; i++) {
			ret[i] = getThumbsPrefix(url, i + 1);
		}
		return ret;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String getTitle(String url) {
		try {
			String page = null;
			if (isId(url)) {
				page = NetUtil.getPage(NetUtil.newURL(url));
			} else {
				page = url;
			}
			//return (new Regex(page, "(?<=<meta name=\"keywords\" content=\").+(?=, redtube)")).getMatches()[0][0].trim();
			//return (new Regex(page, "(?<=<h1 class='videoTitle'>).+(?=</td></h1>)")).getMatches()[0][0].trim();
			return (new Regex(page, "(?<=<h1 class=\"videoTitle\">).+(?=</h1>)")).getMatches()[0][0].trim();
		} catch (Exception e) {
		}
		return null;
	}
}
