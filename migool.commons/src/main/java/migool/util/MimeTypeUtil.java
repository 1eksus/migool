package migool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

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

	// image mime types:

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

	private static final Map<String, Set<String>> MIME_TYPE_EXTENSIONS = new HashMap<String, Set<String>>();

	static {
		// TODO
		MIME_TYPE_EXTENSIONS.put(IMAGE_BMP, new TreeSet<String>(Arrays.asList(BMP)));
		MIME_TYPE_EXTENSIONS.put(IMAGE_GIF, new TreeSet<String>(Arrays.asList(GIF)));
		MIME_TYPE_EXTENSIONS.put(IMAGE_JPEG, new LinkedHashSet<String>(Arrays.asList(JPEG, JPG)));
		MIME_TYPE_EXTENSIONS.put(IMAGE_PNG, new TreeSet<String>(Arrays.asList(PNG)));
		MIME_TYPE_EXTENSIONS.put(IMAGE_TIFF, new TreeSet<String>(Arrays.asList(TIFF)));
		MIME_TYPE_EXTENSIONS.put(IMAGE_XICON, new TreeSet<String>(Arrays.asList(ICO)));
	}

	/**
	 * 
	 * @param mimeType
	 * @return
	 */
	public static String getFileExtensionByMimeType(final String mimeType) {
		final Set<String> extensions = MIME_TYPE_EXTENSIONS.get(mimeType);
		return (extensions != null && !extensions.isEmpty()) ? extensions.iterator().next() : null;
	}

	/**
	 * 
	 * @param extension
	 * @return
	 */
	public static String getMimeTypeByFileExtension(final String extension) {
		final String ext = extension.toLowerCase();
		final Set<Entry<String, Set<String>>> entries = MIME_TYPE_EXTENSIONS.entrySet();
		for (final Entry<String, Set<String>> entry : entries) {
			if (entry.getValue().contains(ext)) {
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
