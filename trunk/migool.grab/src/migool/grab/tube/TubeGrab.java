package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public class TubeGrab implements ITubeGrab {

	private String share;
	private String id;
	private String title;
	private String thumb;
	private String[] thumbs;
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
	public String getShare() {
		return share;
	}

	@Override
	public String getThumb() {
		return thumb;
	}

	@Override
	public String[] getThumbs() {
		return thumbs;
	}

	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * @param share
	 *            the share to set
	 */
	public void setShare(String share) {
		this.share = share;
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
		this.thumb = thumb;
	}

	/**
	 * @param thumbs
	 *            the thumbs to set
	 */
	public void setThumbs(String[] thumbs) {
		this.thumbs = thumbs;
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

}
