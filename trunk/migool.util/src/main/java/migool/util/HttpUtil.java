package migool.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import migool.util.StringUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class HttpUtil {
	public static final String CONTENT_LENGTH = "Content-Length";

	public static final String REFERER = "Referer";

	public static final String COOKIE = "Cookie";

	public static final String SET_COOKIE = "Set-Cookie";
	public static final String SEMICOLON = ";";
	public static final String COOKIES_SEPARATOR = "; ";

	private HttpUtil() {
	}

	public static String getCookies(HttpURLConnection connection) {
		StringBuilder cookies = new StringBuilder();
		boolean isNotFirst = false;
		Map<String, List<String>> headers = connection.getHeaderFields();
		for (String key : headers.keySet()) {
			for (String value : headers.get(key)) {
				if (key != null && key.equals(SET_COOKIE)) {
					if (isNotFirst) {
						cookies.append(COOKIES_SEPARATOR);
					} else {
						isNotFirst = true;
					}
					cookies.append(value.substring(0, value.indexOf(SEMICOLON)));
				}
			}
		}
		return cookies.toString();
	}

	public static String addCookies(String cookies, HttpURLConnection connection) {
		StringBuilder ret = new StringBuilder((StringUtil.isNotNullOrEmpty(cookies)) ? cookies : "");
		String newCookies = getCookies(connection);
		if (StringUtil.isNotNullOrEmpty(newCookies)) {
			ret.append(COOKIES_SEPARATOR);
			ret.append(newCookies);
		}
		return ret.toString();
	}

	public static String toString(InputStream in) {
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = rd.readLine()) != null) {
				sb.append(line + StringUtil.LINE_SEPARATOR);
			}
			return sb.toString();
		} catch (Exception e) {
		}
		return null;
	}
}