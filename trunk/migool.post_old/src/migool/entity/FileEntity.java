package migool.entity;

/**
 * 
 * @author Denis Migol
 *
 */
public class FileEntity {
	public byte[] bytes;
	public String fileName;

	/**
	 * 
	 * @param bytes
	 * @param fileName
	 */
	public FileEntity(byte[] bytes, String fileName) {
		this.bytes = bytes;
		this.fileName = fileName;
	}
}
