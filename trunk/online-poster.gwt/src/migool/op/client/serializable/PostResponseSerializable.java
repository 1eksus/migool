package migool.op.client.serializable;

import java.io.Serializable;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public class PostResponseSerializable implements Serializable {
	public static final int OK = 0;
	public static final int ERROR = 1;
	public static final int NOT_POSTED = 2;

	public int code;
	public String message;
	public String url;

	public PostResponseSerializable(int code, String message, String url) {
		this.code = code;
		this.message = message;
		this.url = url;
	}

	/**
	 * 
	 * @param code
	 * @param url
	 */
	public PostResponseSerializable(int code, String url) {
		this(code, null, url);
	}
}
