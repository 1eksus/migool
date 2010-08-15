package migool.entity;

/**
 * @author Denis Migol
 * 
 */
public class MimeTypeEntity implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7116113463025346928L;

	private String mimeType;
	private byte[] bytes;

	/**
	 * @param mimeType
	 *            the mimeType to set
	 */
	public MimeTypeEntity setMimeType(final String mimeType) {
		this.mimeType = mimeType;
		return this;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param bytes
	 *            the bytes to set
	 */
	public MimeTypeEntity setBytes(final byte[] bytes) {
		this.bytes = bytes;
		return this;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}
}
