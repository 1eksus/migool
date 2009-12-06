package migool.entity;

/**
 * 
 * @author Denis Migol
 *
 */
public class ImageEntity extends FileEntity {
	public static final String BMP = "bmp";
	public static final String GIF = "gif";
	public static final String JPEG = "jpeg";
	public static final String TIFF = "tiff";
	public static final String PNG = "png";

	public String type;

	public ImageEntity(byte[] bytes, String fileName, String type) {
		super(bytes, fileName);
		this.type = type;
	}
}
