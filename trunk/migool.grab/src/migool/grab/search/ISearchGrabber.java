package migool.grab.search;

import migool.grab.IGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ISearchGrabber extends IGrabber {
	ISearchGrab[] grab(String request);
}
