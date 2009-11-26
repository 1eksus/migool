package migool.grab.tube.host.pornhub;

import migool.grab.tube.ITubeGrab;
import migool.grab.tube.TubeGrabberBase;
import migool.util.RegexUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public class PornhubGrabber extends TubeGrabberBase {

	public static final String HOST = "www.pornhub.com";
	public static final String URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/view\\_video\\.php\\?viewkey\\=[\\d]{9}";
	public static final String PAGE_URL_REGEX = "http\\:\\/\\/www\\.pornhub\\.com\\/video\\?o\\=mr\\&page\\=[\\d]+";

	public boolean isUrl(String url) {
		return RegexUtil.isMatch(URL_REGEX, url);
	}

	public boolean isPageUrl(String url) {
		return RegexUtil.isMatch(PAGE_URL_REGEX, url);
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

	@Override
	public String getPageUrl(int number) {
		// TODO Auto-generated method stub
		return null;
	}
}
