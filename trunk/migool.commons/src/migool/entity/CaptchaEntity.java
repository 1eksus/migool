package migool.entity;

/**
 * 
 * @author Denis Migol
 * 
 */
public class CaptchaEntity implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8207886840705101861L;

	private MimeTypeEntity image;
	private String text;

	public static class Builder {
		private final CaptchaEntity entity;

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder() {
			entity = new CaptchaEntity();
		}

		public CaptchaEntity build() {
			return entity;
		}

		public Builder setImage(final MimeTypeEntity image) {
			entity.setImage(image);
			return this;
		}

		public Builder setText(final String text) {
			entity.setText(text);
			return this;
		}
	}

	/**
	 * 
	 */
	public CaptchaEntity() {
		this(null, null);
	}

	/**
	 * 
	 * @param image
	 * @param text
	 * @param result
	 */
	public CaptchaEntity(final MimeTypeEntity image, final String text) {
		this.setImage(image);
		this.setText(text);
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(final MimeTypeEntity image) {
		this.image = image;
	}

	/**
	 * @return the image
	 */
	public MimeTypeEntity getImage() {
		return image;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
}
