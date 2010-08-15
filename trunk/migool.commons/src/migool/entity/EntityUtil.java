package migool.entity;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class EntityUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private EntityUtil() {
	}

	private static final Map<String, String> MIME_TYPE_EXTENSION;

	static {
		// TODO
		MIME_TYPE_EXTENSION = new TreeMap<String, String>();
		MIME_TYPE_EXTENSION.put("image/bmp", "bmp");
		MIME_TYPE_EXTENSION.put("image/gif", ".gif");
		MIME_TYPE_EXTENSION.put("image/jpeg", ".jpeg");
		MIME_TYPE_EXTENSION.put("image/pipeg", "jfif");
		MIME_TYPE_EXTENSION.put("image/png", ".png");
		MIME_TYPE_EXTENSION.put("image/svg+xml", "svg");
		MIME_TYPE_EXTENSION.put("image/tiff", "tiff");
		MIME_TYPE_EXTENSION.put("image/x-icon", "ico");
	}

	public static String mimeTypeToExtention(String mimeType) {
		return MIME_TYPE_EXTENSION.get(mimeType);
	}
}
