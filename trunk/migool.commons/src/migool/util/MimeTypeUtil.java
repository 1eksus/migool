package migool.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Denis Migol
 * 
 */
public final class MimeTypeUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private MimeTypeUtil() {
	}

	// mime types:

	public static final String IMAGE_BMP = "image/bmp";
	public static final String IMAGE_GIF = "image/gif";
	public static final String IMAGE_JPEG = "image/jpeg";
	public static final String IMAGE_PIPEG = "image/pipeg";
	public static final String IMAGE_PNG = "image/png";
	public static final String IMAGE_SVG_XML = "image/svg+xml";
	public static final String IMAGE_TIFF = "image/tiff";
	public static final String IMAGE_XICON = "image/x-icon";

	// file extensions:

	public static final String _BMP = ".bmp";
	public static final String _GIF = ".gif";
	public static final String _JPEG = ".jpeg";
	public static final String _PNG = ".png";
	public static final String _TIFF = ".tiff";
	public static final String _ICO = ".ico";

	private static final Map<String, String> MIME_TYPE_EXTENSION = new HashMap<String, String>();

	static {
		// TODO
		MIME_TYPE_EXTENSION.put(IMAGE_BMP, _BMP);
		MIME_TYPE_EXTENSION.put(IMAGE_GIF, _GIF);
		MIME_TYPE_EXTENSION.put(IMAGE_JPEG, _JPEG);
		MIME_TYPE_EXTENSION.put(IMAGE_PNG, _PNG);
		MIME_TYPE_EXTENSION.put(IMAGE_TIFF, _TIFF);
		MIME_TYPE_EXTENSION.put(IMAGE_XICON, _ICO);
	}

	/**
	 * 
	 * @param mimeType
	 * @return
	 */
	public static String getFileExtensionByMimeType(final String mimeType) {
		return MIME_TYPE_EXTENSION.get(mimeType);
	}

	/**
	 * 
	 * @param fileExtension
	 * @return
	 */
	public static String getMimeTypeByFileExtension(final String fileExtension) {
		final Set<Entry<String, String>> entries = MIME_TYPE_EXTENSION.entrySet();
		for (final Entry<String, String> entry : entries) {
			if (entry.getValue().equalsIgnoreCase(fileExtension)) {
				return entry.getKey();
			}
		}
		return null;
	}
}