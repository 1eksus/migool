package migool.host.upload;

/**
 * 
 * @author Denis Migol
 *
 */
public class UploadResponse {
	public static final int OK = 0;
	public static final int ERROR = 1;
	public static final int NOT_UPLOADED = 2;

	private int code;
	private String value;

	/**
	 * @param code
	 * @param value
	 */
	public UploadResponse(int code, String value) {
		this.setCode(code);
		this.setValue(value);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
