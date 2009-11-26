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
	 * @param share
	 *            the share to set
	 */
	public void setUrl(String share) {
		this.url = share;
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
	 * @param thumb
	 *            the thumb to set
	 */
	public void setThumb(String thumb) {
		this.thumbUrl = thumb;
	}

	/**
	 * @param thumbs
	 *            the thumbs to set
	 */
	public void setThumbs(String[] thumbs) {
		this.thumbUrls = thumbs;
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
