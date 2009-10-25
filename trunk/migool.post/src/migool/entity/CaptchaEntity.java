package migool.entity;

/**
 * 
 * @author Denis Migol
 *
 */
public class CaptchaEntity {
	public ImageEntity image;
	public String title;
	public String result;

	public CaptchaEntity(ImageEntity image, String title, String result) {
		this.image = image;
		this.title = title;
		this.result = result;
	}
}
