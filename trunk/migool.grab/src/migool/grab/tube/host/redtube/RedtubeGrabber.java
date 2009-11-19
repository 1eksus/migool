package migool.grab.tube.host.redtube;

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
	protected void updateTubePosition() {
		// TODO Auto-generated method stub
		
	}
}
