package migool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static migool.util.StringUtil.*;

/**
 * 
 * @author Denis Migol
 *
 */
public class DebugUtil {
	
	public static final String LINE_SEPARATOR = "\n";
	
	public static final String print(InputStream in) throws IOException {
		StringBuilder ret = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			ret.append(line + CRLF);
		}
		in.close();
		return ret.toString();
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static final String toString(List<String> strings) {
		StringBuffer sb = new StringBuffer();
		for (String string : strings) {
			sb.append(string + LINE_SEPARATOR);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static final String toString(String[] strings) {
		StringBuffer sb = new StringBuffer();
		for (String string : strings) {
			sb.append(string + LINE_SEPARATOR);
		}
		return sb.toString();
	}
}
