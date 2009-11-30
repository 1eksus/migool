package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ITubeGrabBuilder {
	ITubeGrabBuilder setIdUrl(String idUrl);

	ITubeGrabBuilder setId(String id);

	ITubeGrabBuilder setTitle(String title);

	ITubeGrabBuilder setMessage(String message);

	ITubeGrabBuilder setThumbUrl(String thumbUrl);

	ITubeGrabBuilder setThumbUrls(String[] thumbUrls);

	ITubeGrabBuilder setDuration(String duration);

	ITubeGrabBuilder setEmbed(String embed);

	ITubeGrab build();
}
