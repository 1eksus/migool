package migool.http.client;

import migool.util.EmptyChecker;

/**
 * 
 * @author Denis Migol
 *
 */
public final class HttpClientUtil {
	public static final String REFERER = "Referer";
	
	private static final String CHARSET = "charset=";

	private HttpClientUtil() {
	}

	public static final String parseCharset(String string) {
		if (EmptyChecker.isNotNullOrEmpty(string) && string.contains(CHARSET)) {
			return string.substring(string.lastIndexOf(CHARSET) + CHARSET.length(), string.length());
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(parseCharset("text/html; charset=windows-1251"));
	}
}
