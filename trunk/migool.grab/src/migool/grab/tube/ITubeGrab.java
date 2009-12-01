package migool.grab.tube;

import migool.grab.IGrab;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ITubeGrab extends IGrab {
	/**
	 * 
	 * @return
	 */
	String getIdUrl();

	/**
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 
	 * @return
	 */
	String getTitle();

	/**
	 * 
	 * @return
	 */
	String getMessage();

	/**
	 * 
	 * @return
	 */
	String getThumbUrl();

	/**
	 * 
	 * @return
	 */
	String[] getThumbUrls();

	/**
	 * 
	 * @return
	 */
	String getDuration();

	/**
	 * 
	 * @return
	 */
	String getEmbed();
}
