package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public abstract class TubeGrabberBase implements ITubeGrabber {

	@Override
	public ITubeGrab[] grab(String url) {
		if (isPageUrl(url)) {
			return grabPageUrl(url);
		} else if (isUrl(url)) {
			return new ITubeGrab[] { grabUrl(url) };
		}
		throw new IllegalArgumentException(url);
	}
}
