package migool.entity;

/**
 * 
 * @author Denis Migol
 *
 */
public class CaptchaEntity implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8207886840705101861L;

	private FileEntity image;
	private String text;
	private String result;

	/**
	 * 
	 */
	public CaptchaEntity() {
	}

	/**
	 * 
	 * @param image
	 * @param text
	 * @param result
	 */
	public CaptchaEntity(FileEntity image, String text, String result) {
		this.setImage(image);
		this.setText(text);
		this.setResult(result);
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(FileEntity image) {
		this.image = image;
	}

	/**
	 * @return the image
	 */
	public FileEntity getImage() {
		return image;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
}
