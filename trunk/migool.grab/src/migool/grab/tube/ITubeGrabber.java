package migool.grab.tube;

import migool.grab.IGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ITubeGrabber extends IGrabber {
	/**
	 * 
	 * @param url
	 * @return
	 */
	boolean isUrl(String url);

	/**
	 * 
	 * @param url
	 * @return
	 */
	boolean isPageUrl(String url);

	/**
	 * 
	 * @param url
	 * @return
	 */
	ITubeGrab[] grab(String url);

	/**
	 * 
	 * @param url
	 * @return
	 */
	ITubeGrab grabUrl(String url);

	/**
	 * 
	 * @param url
	 * @return
	 */
	ITubeGrab[] grabPageUrl(String url);

	/**
	 * 
	 * @param number
	 * @return
	 */
	String getPageUrl(int number);
}