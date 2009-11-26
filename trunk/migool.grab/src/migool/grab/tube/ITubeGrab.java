package migool.grab.tube;

import migool.grab.IGrab;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ITubeGrab extends IGrab {
	String getUrl();

	String getId();

	String getTitle();

	String getMessage();

	String getThumbUrl();

	String[] getThumbUrls();

	String getDuration();

	String getEmbed();
}
