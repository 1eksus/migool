package migool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import migool.entity.MimeTypeEntity;

import org.apache.commons.io.FilenameUtils;

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

	public static final Set<String> IMAGE_MIME_TYPES;
	static {
		IMAGE_MIME_TYPES = new HashSet<String>();
		IMAGE_MIME_TYPES.add(IMAGE_BMP);
		IMAGE_MIME_TYPES.add(IMAGE_GIF);
		IMAGE_MIME_TYPES.add(IMAGE_JPEG);
		IMAGE_MIME_TYPES.add(IMAGE_PIPEG);
		IMAGE_MIME_TYPES.add(IMAGE_PNG);
		IMAGE_MIME_TYPES.add(IMAGE_SVG_XML);
		IMAGE_MIME_TYPES.add(IMAGE_TIFF);
		IMAGE_MIME_TYPES.add(IMAGE_XICON);
	}

	// file extensions:

	public static final String BMP = "bmp";
	public static final String GIF = "gif";
	public static final String JPEG = "jpeg";
	public static final String JPG = "jpg";
	public static final String PNG = "png";
	public static final String TIFF = "tiff";
	public static final String ICO = "ico";

	private static final Map<String, String> MIME_TYPE_EXTENSION = new HashMap<String, String>();

	static {
		// TODO
		MIME_TYPE_EXTENSION.put(IMAGE_BMP, BMP);
		MIME_TYPE_EXTENSION.put(IMAGE_GIF, GIF);
		MIME_TYPE_EXTENSION.put(IMAGE_JPEG, JPEG);
		MIME_TYPE_EXTENSION.put(IMAGE_JPEG, JPG);
		MIME_TYPE_EXTENSION.put(IMAGE_PNG, PNG);
		MIME_TYPE_EXTENSION.put(IMAGE_TIFF, TIFF);
		MIME_TYPE_EXTENSION.put(IMAGE_XICON, ICO);
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
	 * @param extension
	 * @return
	 */
	public static String getMimeTypeByFileExtension(final String extension) {
		final Set<Entry<String, String>> entries = MIME_TYPE_EXTENSION.entrySet();
		for (final Entry<String, String> entry : entries) {
			if (entry.getValue().equalsIgnoreCase(extension)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static MimeTypeEntity fileToMimeTypeEntity(final File file) throws FileNotFoundException, IOException {
		final byte[] bytes = IOUtil.toByteArray(new FileInputStream(file));
		final String mimeType = getMimeTypeByFileExtension(FilenameUtils.getExtension(file.getName()));
		return new MimeTypeEntity(mimeType, bytes);
	}

	public static boolean isImage(final String mimeType) {
		return IMAGE_MIME_TYPES.contains(mimeType.toLowerCase());
	}
}
