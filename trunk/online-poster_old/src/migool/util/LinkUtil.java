package migool.util;

import java.net.URL;

/**
 * 
 * @author Denis Migol
 *
 */
public abstract class LinkUtil {
	public static final String SLASH = "/";
	public static final String HTTP_PREFIX = "http://";
	public static final String HTTPS_PREFIX = "https://";
	public static final String FTP_PREFIX = "ftp://";

	public static final String WWW_PREFIX = "www.";

	/**
	 * 
	 * @param link
	 * @return
	 */
	public static String cutLinkToHost(final String link) {
		if (EmptyChecker.isNullOrEmpty(link))
			return link;
		String result = link;
		if (link.startsWith(HTTP_PREFIX) || link.startsWith(HTTPS_PREFIX)
				|| link.startsWith(FTP_PREFIX)) {
			if (link.startsWith(HTTP_PREFIX)) {
				result = link.substring(HTTP_PREFIX.length());
			} else if (link.startsWith(HTTPS_PREFIX)) {
				result = link.substring(HTTPS_PREFIX.length());
			} else {// if (link.startsWith(FTP_PREFIX)){
				result = link.substring(FTP_PREFIX.length());
			}
		}
		if (result.indexOf("/") > -1) {
			result = result.substring(0, result.indexOf("/"));
		}
		if (result.startsWith(WWW_PREFIX)) {
			result = result.substring(WWW_PREFIX.length());
		}
		return result;
	}

	/**
	 * 
	 * @param link
	 * @return
	 */
	public static String getHost(final String link) {
		String host = cutLinkToHost(link);
		if (EmptyChecker.isNotNullOrEmpty(host)) {
			host = host.toLowerCase();
			while (host.startsWith(WWW_PREFIX)) {
				host = host.substring(WWW_PREFIX.length());
			}
			if (host.endsWith(".")) {
				host = host.substring(0, host.length() - 1);
			}
			if (host.indexOf(".") == 1) {
				return "";
			}
			try {
				URL url = new URL(HTTP_PREFIX + host);
				url.openConnection();
				return url.getHost();
			} catch (Exception e) {
				return "";
			}
		}
		return host;
	}
	
	/**
	 * 
	 * @param link
	 * @return
	 */
	public static String createHttpRoot(final String link) {
		String host = cutLinkToHost(link);
		if (EmptyChecker.isNotNullOrEmpty(host)) {
			return HTTP_PREFIX + host;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param link
	 * @param path
	 * @return
	 */
	public static String createHttpLink(final String link, final String path) {
		String httpRoot = createHttpRoot(link);
		if (EmptyChecker.isNotNullOrEmpty(httpRoot) && EmptyChecker.isNotNullOrEmpty(StringUtil.cutPrefix(path, SLASH))) {
			return httpRoot + SLASH + path;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param link
	 * @return
	 */
	public static boolean isRootLink(String link) {
		if (EmptyChecker.isNullOrEmpty(link) || !RegexUtil.isUrl(link)) {
			return false;
		}
		String host = getHost(link);
		return EmptyChecker.isNotNullOrEmpty(host)
				&& (link.endsWith(host) || link.endsWith(host + "/"));
	}

	public static String getFileName(String url) {
		if (EmptyChecker.isNullOrEmpty(url)) {
			return url;
		}
		String result = url;
		int lastIndex = 0;

		// cut prefix
		if (result.contains("/")) {
			lastIndex = result.lastIndexOf("/");
		} else if (result.contains("\\")) {
			lastIndex = result.lastIndexOf("\\");
		}
		result = result.substring(lastIndex + 1);

		if (EmptyChecker.isNullOrEmpty(result)) {
			return result;
		}
		
		// cut suffix
		while (!RegexUtil.isFileNameUrl(result) && EmptyChecker.isNotNullOrEmpty(result)) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
}
