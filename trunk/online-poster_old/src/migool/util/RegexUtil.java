package migool.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static final String MD5_HASH_REGEX = "[a-fA-F\\d]{32}";
	public static final String IP_REGEX = "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
	public static final String PROXY_REGEX = IP_REGEX + "\\:[1-9][0-9]+";
	public static final String HOST_REGEX = "^[a-zA-Z0-9\\-\\.]{2,}\\.[a-z]{2,}$";//"^((?:(?:(?:[a-zA-Z0-9][\\.\\-\\+_]?)*)[a-zA-Z0-9])+)\\@((?:(?:(?:[a-zA-Z0-9][\\.\\-_]?){0,62})[a-zA-Z0-9])+)\\.([a-zA-Z0-9]{2,6})$";
	public static final String URL_REGEX = "^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&amp;%\\$#_]*)?$";//"https?://(\\w*:\\w*@)?[-\\w.]+(:\\d+)?(/([\\w/_.]*(\\?\\s+)?)?)?";
	public static final String MAIL_REGEX = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";//"(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+";
	public static final String HTML_DELIMITERS = " \t\n\r\f" + "()<>\"";
	public static final String FILENAME_URL_REGEX = "[\\w][\\w\\$\\.-]*";
	
	public static String getMatch(final String data, final String regex, int i, int j) {
		Regex rx = new Regex(data, regex);
		try {
			return rx.getMatches()[i][j];
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getMatch(final String data, final String regex) {
		return getMatch(data, regex, 0, 0);
	}
	
	public static boolean isMatch(String regex, String string) {
		if (!EmptyChecker.isNotNullOrEmpty(regex) || !EmptyChecker.isNotNullOrEmpty(string)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	public static boolean isMail(String string) {
		return isMatch(MAIL_REGEX, string);
	}

	public static boolean isUrl(String string) {
		return isMatch(URL_REGEX, string);
	}
	
	public static boolean isHost(String string) {
		return isMatch(HOST_REGEX, string);
	}
	
	public static boolean isIp(String string) {
		return isMatch(IP_REGEX, string);
	}
	
	public static boolean isProxy(String string) {
		return isMatch(PROXY_REGEX, string);
	}
	
	public static boolean isFileNameUrl(String string) {
		return isMatch(FILENAME_URL_REGEX, string);
	}
}
