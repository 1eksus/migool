package migool.grab.tube;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.htmlparser.util.ParserException;

/**
 * 
 * @author Denis Migol
 * 
 */
public abstract class TubeGrabberBase implements ITubeGrabber {

	@Override
	public ITubeGrab[] grab(String url) throws ClientProtocolException, ParserException, IOException {
		if (isPageUrl(url)) {
			return grabPageUrl(url);
		} else if (isUrl(url)) {
			return new ITubeGrab[] { grabUrl(url) };
		}
		throw new IllegalArgumentException(url);
	}
}
