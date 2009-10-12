package migool.grab.host.redtube;

import migool.grab.IGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RedtubeGrabber implements IGrabber {

	public static final String HOST = "redtube.com";

	@Override
	public String getHost() {
		return HOST;
	}
}
