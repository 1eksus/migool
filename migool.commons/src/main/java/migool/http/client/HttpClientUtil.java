package migool.http.client;

import migool.util.StringUtil;

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

	public static final String parseCharset(final String string) {
		if (StringUtil.isNotNullOrEmpty(string) && string.contains(CHARSET)) {
			String ret = string.substring(string.lastIndexOf(CHARSET) + CHARSET.length(), string.length());
			final int index = ret.indexOf(' ');
			if (index > -1) {
				ret = ret.substring(0, index);
			}
			if (ret.startsWith("\"")) {
				ret = ret.substring(1);
			}
			if (ret.endsWith("\"")) {
				ret = ret.substring(0, ret.length() - 1);
			}
			return ret;
		} else {
			return null;
		}
	}

	public static void main(final String[] args) {
		System.out.println(parseCharset("text/html; charset=windows-1251"));
	}
}
