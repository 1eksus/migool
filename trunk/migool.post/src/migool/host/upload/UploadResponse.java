package migool.host.upload;

/**
 * 
 * @author Denis Migol
 *
 */
public class UploadResponse {
	public final int result;
	public final String value;

	/**
	 * @param result
	 * @param value
	 */
	public UploadResponse(int result, String value) {
		this.result = result;
		this.value = value;
	}
}
