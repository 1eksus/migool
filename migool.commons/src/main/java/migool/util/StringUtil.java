package migool.util;

import java.util.Collection;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class StringUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private StringUtil() {
	}

	public static final String CRLF = "\r\n";
	public static final String CRLF2 = CRLF + CRLF;
	public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
	public static final String TWO_LINE_SEPARATORS = LINE_SEPARATOR + LINE_SEPARATOR;

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(final String s) {
		return (s == null) || (s.length() == 0);
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotNullOrEmpty(final String s) {
		return (s != null) && (s.length() > 0);
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isNotNullOrEmpty(final String[] strings) {
		return (strings != null) && (strings.length > 0);
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isNullOrEmpty(final String[] strings) {
		return (strings == null) || (strings.length == 0);
	}

	/**
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static String endsWith(final String string, final String[] strings) {
		if (isNullOrEmpty(string) || isNullOrEmpty(strings)) {
			return null;
		}
		final int length = strings.length;
		for (int i = 0; i < length; i++) {
			if (string.endsWith(strings[i])) {
				return strings[i];
			}
		}
		return null;
	}

	/**
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static String equals(final String string, final String[] strings) {
		if (isNullOrEmpty(string) || isNullOrEmpty(strings)) {
			return null;
		}
		final int length = strings.length;
		for (int i = 0; i < length; i++) {
			if (string.equals(strings[i])) {
				return strings[i];
			}
		}
		return null;
	}

	/**
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static String equalsIgnoreCase(final String string, final String[] strings) {
		if (isNullOrEmpty(string) || isNullOrEmpty(strings)) {
			return null;
		}
		final int length = strings.length;
		for (int i = 0; i < length; i++) {
			if (string.equalsIgnoreCase(strings[i])) {
				return strings[i];
			}
		}
		return null;
	}

	/**
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static String contains(final String string, final String[] strings) {
		if (isNullOrEmpty(string) || isNullOrEmpty(strings)) {
			return null;
		}
		final int length = strings.length;
		for (int i = 0; i < length; i++) {
			if (string.contains(strings[i])) {
				return strings[i];
			}
		}
		return null;
	}

	/**
	 * 
	 * @param strings
	 * @param string
	 * @return
	 */
	public static String contains(final Collection<String> strings, final String string) {
		if (strings != null && string != null) {
			for (final String elem : strings) {
				if (elem != null && elem.contains(string)) {
					return elem;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static String contains(final String string, final Collection<String> strings) {
		if (strings != null && string != null) {
			for (final String elem : strings) {
				if (elem != null && string.contains(elem)) {
					return elem;
				}
			}
		}
		return null;
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
		if (isNullOrEmpty(source) || isNullOrEmpty(prefix)) {
			return source;
		}
		return source.startsWith(prefix) ? source.substring(prefix.length()) : source;
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
		if (isNullOrEmpty(source) || isNullOrEmpty(suffix)) {
			return source;
		}
		return source.endsWith(suffix) ? source.substring(0, source.length() - suffix.length()) : source;
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
		if (isNullOrEmpty(source) || isNullOrEmpty(begin) || isNullOrEmpty(end)) {
			return source;
		}
		String result = null;
		int beginIndex = source.indexOf(begin);
		if (beginIndex != -1)
			beginIndex += begin.length();
		final int endIndex = source.indexOf(end, beginIndex);
		try {
			result = source.substring(beginIndex, endIndex);
		} catch (final Exception e) {
		}
		return result;
	}

	/**
	 * 
	 * @param s
	 * @param length
	 * @return
	 */
	public static String appendPrefixWithZeros(final String s, final int length) {
		final StringBuilder ret = new StringBuilder((s == null) ? "" : s);
		for (int i = ret.length(); i < length; i++) {
			ret.insert(0, '0');
		}
		return ret.toString();
	}

	public static final String appendSuffixWithChars(final String string, final int length, final char ch) {
		final StringBuilder ret = new StringBuilder((string == null) ? "" : string);
		for (int i = ret.length(); i < length; i++) {
			ret.append(ch);
		}
		return ret.toString();
	}

	/**
	 * 
	 * @param string
	 * @param ch
	 * @return
	 */
	public static final int count(final String string, final char ch) {
		int ret = 0;
		if (isNotNullOrEmpty(string)) {
			for (final char c : string.toCharArray()) {
				if (c == ch) {
					ret++;
				}
			}
		}
		return ret;
	}

	public static boolean equalsIgnoreCaseSkipSpaces(final String s1, final String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}

		final String string1 = s1.trim().toLowerCase();
		final String string2 = s2.trim().toLowerCase();

		if (string1.equals(string2)) {
			return true;
		} else {
			final int length1 = string1.length() - 1;
			final int length2 = string2.length() - 1;

			if (length1 == -1 || length2 == -1) {
				return false;
			}

			int i1 = 0;
			int i2 = 0;
			boolean a = i1 >= 0;
			boolean b = i2 >= 0;
			boolean ret = true;

			while (ret & (a || b)) {
				if (a && b) {
					final char c1 = string1.charAt(i1);
					final char c2 = string2.charAt(i2);

					if (c1 == c2) {
						if (i1 < length1) {
							i1++;
						} else {
							i1 = -1;
						}
						if (i2 < length2) {
							i2++;
						} else {
							i2 = -1;
						}
					} else if (c1 == ' ') {
						if (i1 < length1) {
							i1++;
						} else {
							i1 = -1;
						}
					} else if (c2 == ' ') {
						if (i2 < length2) {
							i2++;
						} else {
							i2 = -1;
						}
					} else {
						ret = false;
					}
				} else if (a) { // i1 >= 0
					final char c1 = string1.charAt(i1);
					if (c1 == ' ') {
						if (i1 < length1) {
							i1++;
						} else {
							i1 = -1;
						}
					} else {
						ret = false;
					}
				} else { // i2 >= 0
					final char c2 = string2.charAt(i2);
					if (c2 == ' ') {
						if (i2 < length2) {
							i2++;
						} else {
							i2 = -1;
						}
					} else {
						ret = false;
					}
				}
				a = i1 >= 0;
				b = i1 >= 0;
			} // while
			return ret;
		}
	}

	public static String removeChars(final String string, final String chars) {
		if (string == null) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		final int stringLength = string.length();
		final int charsLength = chars.length();
		for (int i = 0; i < stringLength; i++) {
			final char c = string.charAt(i);
			boolean found = false;
			for (int j = 0; j < charsLength; j++) {
				if (c == chars.charAt(j)) {
					found = true;
				}
			}
			if (!found) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// public static void main(String[] args) {
	// System.out.println(appendPrefixWithZeros("asdf", 10));
	// System.out.println(appendPrefixWithZeros(null, 10));
	// System.out.println(appendPrefixWithZeros("asdfasdfas", 10));
	// System.out.println(appendPrefixWithZeros("asdfasdfasas", 10));
	// }
}
