package migool.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class URLUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private URLUtil() {
	}

	/**
	 * 
	 * @return
	 */
	public static String getHost(final String url) {
		try {
			return new URL(url).getHost();
		} catch (MalformedURLException e) {
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String getPath(final String url) {
		try {
			return new URL(url).getPath();
		} catch (MalformedURLException e) {
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static int getPort(final String url) {
		try {
			return new URL(url).getPort();
		} catch (MalformedURLException e) {
		}
		return -1;
	}

	private static final String DOMAIN_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";
	private static final Pattern DOMAIN_PATTERN = Pattern.compile(DOMAIN_REGEX, Pattern.CASE_INSENSITIVE
			| Pattern.DOTALL);

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isDomain(final String string) {
		return string == null ? false : DOMAIN_PATTERN.matcher(string).matches();
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String getDomain(final String string) {
		if (isDomain(string)) {
			return string;
		}
		final String host = getHost(string);
		return isDomain(host) ? host : null;
	}

	/**
	 * Test for second-level domain
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isSLD(final String string) {
		return isDomain(string) && StringUtil.count(string, '.') == 1;
	}
}
