package migool.op.client.serializable;

import java.io.Serializable;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public class PostResponse implements Serializable {
	public static final int OK = 0;
	public static final int ERROR = 1;
	public static final int NOT_POSTED = 2;

	public int code;
	public String url;

	/**
	 * 
	 * @param code
	 * @param url
	 */
	public PostResponse(int code, String url) {
		this.code = code;
		this.url = url;
	}
}
