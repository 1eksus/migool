package migool.entity;

/**
 * 
 * @author Denis Migol
 *
 */
public class CaptchaEntity {
	public ImageEntity image;
	public String text;
	public String result;

	public CaptchaEntity(ImageEntity image, String text, String result) {
		this.image = image;
		this.text = text;
		this.result = result;
	}
}
