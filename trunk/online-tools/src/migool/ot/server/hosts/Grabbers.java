package migool.ot.server.hosts;

import java.util.HashMap;
import java.util.Map;

import migool.grab.host.redtube.RedtubeGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class Grabbers {
	private Grabbers() {
	}

	public static final Map<String, RedtubeGrabber> grabbers;

	static {
		grabbers = new HashMap<String, RedtubeGrabber>();
		grabbers.put(RedtubeGrabber.HOST, new RedtubeGrabber());
	}
}
