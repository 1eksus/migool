package migool.entity;

/**
 * 
 * @author Denis Migol
 * 
 */
public class FileEntity implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2508470486519337339L;

	// public static final String BMP = "bmp";
	// public static final String GIF = "gif";
	// public static final String JPEG = "jpeg";
	// public static final String TIFF = "tiff";
	// public static final String PNG = "png";

	private String mimeType;
	private byte[] bytes;
	private String fileName;

	/**
	 * The constructor
	 */
	public FileEntity() {
	}

	/**
	 * 
	 * @param mimeType
	 * @param bytes
	 * @param fileName
	 */
	public FileEntity(final String mimeType, final byte[] bytes, final String fileName) {
		this.mimeType = mimeType;
		this.bytes = bytes;
		this.fileName = fileName;
	}

	/**
	 * @param bytes
	 *            the bytes to set
	 */
	public void setBytes(final byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param mimeType
	 *            the mimeType to set
	 */
	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}
}
