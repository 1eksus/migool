package migool.poster;

/**
 * 
 * @author Denis Migol
 *
 */
public final class PostResponse {
	public static final int OK = 0;
	public static final int ERROR = 1;
	public static final int NOT_POSTED = 2;

	private int code;
	private String url;

	public PostResponse(int code, String url) {
		this.setCode(code);
		this.setUrl(url);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
