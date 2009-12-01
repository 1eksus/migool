package migool.grab.tube;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.htmlparser.util.ParserException;

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
	boolean isIdUrl(String url);

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
	 * @throws IOException 
	 * @throws ParserException 
	 * @throws ClientProtocolException 
	 */
	ITubeGrab[] grab(String url) throws ClientProtocolException, ParserException, IOException;

	/**
	 * 
	 * @param url
	 * @return
	 */
	ITubeGrab grabIdUrl(String url);

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParserException 
	 */
	ITubeGrab[] grabPageUrl(String url) throws ClientProtocolException, IOException, ParserException;

	/**
	 * 
	 * @param number
	 * @return
	 */
	String getPageUrl(int number);
}