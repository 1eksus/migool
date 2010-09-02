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

	public static class Builder {
		private final MimeTypeEntity entity;

		public Builder() {
			entity = new MimeTypeEntity();
		}

		public MimeTypeEntity build() {
			return entity;
		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder setMimeType(final String mimeType) {
			entity.setMimeType(mimeType);
			return this;
		}

		public Builder setBytes(final byte[] bytes) {
			entity.setBytes(bytes);
			return this;
		}
	}

	/**
	 * 
	 */
	public MimeTypeEntity() {
		this(null, null);
	}

	/**
	 * 
	 * @param mimeType
	 * @param bytes
	 */
	public MimeTypeEntity(final String mimeType, final byte[] bytes) {
		super();
		this.mimeType = mimeType;
		this.bytes = bytes;
	}

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
