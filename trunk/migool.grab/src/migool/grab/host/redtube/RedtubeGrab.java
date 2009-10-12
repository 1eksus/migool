package migool.grab.host.redtube;

import java.util.Arrays;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RedtubeGrab {
	public String share;
	public String title;
	public String thumb;
	public String[] thumbs;
	public String duration;
	public String embed;

	@Override
	public String toString() {
		return "RedtubeGrab [duration=" + duration + ", embed=" + embed + ", share=" + share + ", thumb=" + thumb
				+ ", thumbs=" + Arrays.toString(thumbs) + ", title=" + title + "]";
	}
}
