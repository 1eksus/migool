package migool.share.image;

/**
 * 
 * @author Denis Migol
 *
 */
public final class ImageShareResponse {
	private int code;
	private String link;
	private String thumbLink;
	
	/**
	 * 
	 */
	public ImageShareResponse() {
		super();
	}

	/**
	 * 
	 * @param code
	 * @param link
	 * @param thumbLink
	 */
	public ImageShareResponse(int code, String link, String thumbLink) {
		super();
		this.setCode(code);
		this.setLink(link);
		this.setThumbLink(thumbLink);
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param thumbLink the thumbLink to set
	 */
	public void setThumbLink(String thumbLink) {
		this.thumbLink = thumbLink;
	}

	/**
	 * @return the thumbLink
	 */
	public String getThumbLink() {
		return thumbLink;
	}
}
