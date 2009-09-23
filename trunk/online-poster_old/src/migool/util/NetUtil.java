/**
 * 
 */
package migool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Denis Migol
 *
 */
public final class NetUtil {
	
	public static String getPage(URL url) {
		try {
			StringBuffer ret = new StringBuffer("");
			URLConnection connection = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				ret.append(line + StringUtil.CRLF);
			}
			return ret.toString();
		} catch (IOException e) {
			return null;
		}
	}

	public static String getPage(String url) {
		try {
			return getPage(new URL(url));
		} catch (Exception e) {
			return null;
		}
	}

	public static URL newURL(String url) {
		try {
			return new URL(url);
		} catch (Exception e) {
		}
		return null;
	}
}
