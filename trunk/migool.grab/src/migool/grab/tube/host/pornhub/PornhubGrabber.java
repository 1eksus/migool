package migool.grab.tube.host.pornhub;

import migool.grab.tube.ITubeGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public class PornhubGrabber implements ITubeGrabber {

	public static final String HOST = "pornhub.com";

	@Override
	public String getHost() {
		return HOST;
	}
}
