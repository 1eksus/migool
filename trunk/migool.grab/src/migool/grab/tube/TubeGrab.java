package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public class TubeGrab implements ITubeGrab {

	private String url;
	private String id;
	private String title;
	private String message;
	private String thumbUrl;
	private String[] thumbUrls;
	private String duration;
	private String embed;

	@Override
	public String getDuration() {
		return duration;
	}

	@Override
	public String getEmbed() {
		return embed;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getThumbUrl() {
		return thumbUrl;
	}

	@Override
	public String[] getThumbUrls() {
		return thumbUrls;
	}

	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param thumbUrl
	 *            the thumbUrl to set
	 */
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	/**
	 * @param thumbUrls
	 *            the thumbUrls to set
	 */
	public void setThumbUrls(String[] thumbUrls) {
		this.thumbUrls = thumbUrls;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @param embed
	 *            the embed to set
	 */
	public void setEmbed(String embed) {
		this.embed = embed;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}