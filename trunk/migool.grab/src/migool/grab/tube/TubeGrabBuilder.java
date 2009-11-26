package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public class TubeGrabBuilder implements ITubeGrabBuilder {

	private final TubeGrab grab;

	/**
	 * The constructor.
	 */
	public TubeGrabBuilder() {
		grab = new TubeGrab();
	}

	@Override
	public ITubeGrab build() {
		return grab;
	}

	@Override
	public ITubeGrabBuilder setDuration(String duration) {
		grab.setDuration(duration);
		return this;
	}

	@Override
	public ITubeGrabBuilder setEmbed(String embed) {
		grab.setEmbed(embed);
		return this;
	}

	@Override
	public ITubeGrabBuilder setId(String id) {
		grab.setId(id);
		return this;
	}

	@Override
	public ITubeGrabBuilder setMessage(String message) {
		grab.setMessage(message);
		return this;
	}

	@Override
	public ITubeGrabBuilder setThumbUrl(String thumbUrl) {
		grab.setThumbUrl(thumbUrl);
		return this;
	}

	@Override
	public ITubeGrabBuilder setThumbUrls(String[] thumbUrls) {
		grab.setThumbUrls(thumbUrls);
		return this;
	}

	@Override
	public ITubeGrabBuilder setTitle(String title) {
		grab.setTitle(title);
		return this;
	}

	@Override
	public ITubeGrabBuilder setUrl(String url) {
		grab.setUrl(url);
		return this;
	}
}
