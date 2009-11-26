package migool.grab.tube.host.pornhub;

import migool.grab.tube.ITubeGrab;
import migool.grab.tube.TubeGrabberBase;

/**
 * 
 * @author Denis Migol
 * 
 */
public class PornhubGrabber extends TubeGrabberBase {

	public static final String HOST = "pornhub.com";

	public boolean isUrl(String url) {
		// TODO
		return false;
	}

	public boolean isPageUrl(String url) {
		// TODO
		return false;
	}

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
}
