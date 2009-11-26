package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ITubeGrab {
	String getUrl();

	String getId();

	String getTitle();

	String getMessage();

	String getThumbUrl();

	String[] getThumbUrls();

	String getDuration();

	String getEmbed();
}
