package migool.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class ImageType {
	private ImageType() {
	}

	public static final String BMP = "bmp";
	public static final String GIF = "gif";
	public static final String JPEG = "jpeg";
	public static final String TIFF = "tiff";
	public static final String PNG = "png";

	private static final Map<String, byte[][]> imageTypes;
	private static final Map<String, String[]> contentTypes;

	static {
		contentTypes = new HashMap<String, String[]>();
		contentTypes.put(BMP, new String[]{"image/bmp"});
		contentTypes.put(GIF, new String[]{"image/gif"});
		contentTypes.put(JPEG, new String[]{"image/jpeg", "image/pjpeg"});
		contentTypes.put(TIFF, new String[]{"image/tiff"});
		contentTypes.put(PNG, new String[]{"image/png", "image/x-png"});

		imageTypes = new HashMap<String, byte[][]>();
		imageTypes.put(BMP, new byte[][] { new byte[] { (byte) 0x42, (byte) 0x4D } });
		imageTypes.put(GIF, new byte[][] { new byte[] { (byte) 0x44, (byte) 0x49, (byte) 0x46 } });
		imageTypes.put(JPEG, new byte[][] { new byte[] { (byte) 0xFF, (byte) 0xD8 } });
		imageTypes.put(TIFF, new byte[][] { new byte[] { (byte) 0x49, (byte) 0x49} });
		imageTypes.put(PNG, new byte[][] { new byte[] { (byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47 } });
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static final String getImageTypeByImage(byte[] bytes) {
		Map<String, byte[][]> imgTypes = ImageType.imageTypes;
		Set<String> types = imgTypes.keySet();

		int count;
		int size;
		byte[][] bb;
		byte[] b;
		boolean equals;
		for (String imgType : types) {
			bb = imgTypes.get(imgType);
			count = bb.length;
			for (int k = 0; k < count; k++) {
				b = bb[k];
				size = b.length;
				equals = true;
				for (int i = 0; i < size; i++) {
					if (bytes[i] != b[i]) {
						equals = false;
					}
				}
				if (equals) {
					return imgType;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param imageType
	 * @return
	 */
	public static final String getImageTypeByContentType(String contentType) {
		Map<String, String[]> contentTypes = ImageType.contentTypes;
		Set<String> imgTypes = contentTypes.keySet();

		String[] cntTypes;
		String cntType;
		int size;
		for (String imgType : imgTypes) {
			cntTypes = contentTypes.get(imgType);
			size = cntTypes.length;
			for (int i = 0; i < size; i++) {
				cntType = cntTypes[i];
				if (cntType.equalsIgnoreCase(contentType)) {
					return imgType;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param imageType
	 * @return
	 */
	public static final String getContentTypeByImageType(String imageType) {
		String[] cntTypes = contentTypes.get(imageType);
		return (EmptyChecker.isNotNullOrEmpty(cntTypes)) ? cntTypes[0] : null;
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static final String getContentTypeByImage(byte[] bytes) {
		return getContentTypeByImageType(getImageTypeByImage(bytes));
	}
}
