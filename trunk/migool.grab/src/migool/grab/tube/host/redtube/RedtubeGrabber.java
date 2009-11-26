package migool.grab.tube.host.redtube;

import migool.grab.tube.ITubeGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RedtubeGrabber implements ITubeGrabber {

	public static final String HOST = "redtube.com";

	@Override
	public String getHost() {
		return HOST;
	}
}
