package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ITubeGrab {
	String getShare();

	String getId();

	String getTitle();

	String getThumbUrl();

	String[] getThumbUrls();

	String getDuration();

	String getEmbed();
}
