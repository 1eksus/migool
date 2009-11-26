package migool.grab.tube.host.redtube;

import migool.grab.tube.ITubeGrab;
import migool.grab.tube.TubeGrabberBase;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RedtubeGrabber extends TubeGrabberBase {

	public static final String HOST = "redtube.com";

	@Override
	public String getHost() {
		return HOST;
	}

	@Override
	public ITubeGrab[] grabPageUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITubeGrab grabUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPageUrl(String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUrl(String url) {
		// TODO Auto-generated method stub
		return false;
	}
}
