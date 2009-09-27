package migool.post.tag;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class TagUtil {

	public static final String SPACE = " ";
	public static final String COMMA = ",";
	public static final String COMMA_WITH_SPACE = ", "; // default

	/**
	 * 
	 * @return
	 */
	public static final String toString(List<String> tags) {
		return toString(tags, COMMA_WITH_SPACE);
	}

	/**
	 * 
	 * @return
	 */
	public static final String toString(List<String> tags, String separator) {
		Iterator<String> i = tags.iterator();
		if (i.hasNext()) {
			StringBuilder sb = new StringBuilder();
			for (;;) {
				sb.append(i.next());
				if (!i.hasNext()) {
					return sb.toString();
				}
				sb.append(separator);
			}
		}
		return "";
	}
}
