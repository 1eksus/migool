/**
 * 
 */
package migool.util;

/**
 * @author Denis Migol
 *
 */
public final class StringUtil {
	public static final String CRLF = "\r\n";
	
	/**
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static boolean equals(String string, String[] strings) {
		if (!EmptyChecker.isNotNullOrEmpty(string) || !EmptyChecker.isNotNullOrEmpty(strings)) {
			return false;
		}
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			if (EmptyChecker.isNotNullOrEmpty(item) && item.equals(string)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static boolean equalsIgnoreCase(String string, String[] strings) {
		if (!EmptyChecker.isNotNullOrEmpty(string) || !EmptyChecker.isNotNullOrEmpty(strings)) {
			return false;
		}
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			if (EmptyChecker.isNotNullOrEmpty(item) && item.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;		
	}

	/**
	 * Gets string without prefix.
	 * 
	 * @param source
	 * @param prefix
	 * @return source, if it doesn't start with prefix, source without prefix
	 *         otherwise.
	 */
	public static String cutPrefix(final String source, final String prefix) {
		if (!EmptyChecker.isNotNullOrEmpty(source) || !EmptyChecker.isNotNullOrEmpty(prefix)) {
			return source;
		}
		String res = source;
		if (source.startsWith(prefix)) {
			res = source.substring(prefix.length());
		}
		return res;
	}

	/**
	 * Gets string without suffix.
	 * 
	 * @param source
	 * @param suffix
	 * @return source, if it doesn't end with suffix, source without suffix
	 *         otherwise.
	 */
	public static String cutSuffix(final String source, final String suffix) {
		if (!EmptyChecker.isNotNullOrEmpty(source) || !EmptyChecker.isNotNullOrEmpty(suffix)) {
			return source;
		}
		String res = source;
		if (source.endsWith(suffix)) {
			res = source.substring(0, res.length() - suffix.length());
		}
		return res;
	}

	/**
	 * Gets substring of string between begin and end substrings.
	 * 
	 * @param source
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String getBetween(final String source, final String begin, final String end) {
		if (!EmptyChecker.isNotNullOrEmpty(source) || !EmptyChecker.isNotNullOrEmpty(begin) || !EmptyChecker.isNotNullOrEmpty(end)) {
			return source;
		}
		String result = null;
		int beginIndex = source.indexOf(begin);
		if (beginIndex != -1)
			beginIndex += begin.length();
		int endIndex = source.indexOf(end, beginIndex);
		try {
			result = source.substring(beginIndex, endIndex);
		} catch (Exception e) {
		}
		return result;
	}
}