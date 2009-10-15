package pornohouse.net.ru;

import java.util.List;

import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public class PornohousePost {
	public String url;
	public String title;
	public List<String> categories;
	public String brief;
	public String embed;
	public String duration;
	public List<Image> images;

	@Override
	public String toString() {
		return "PornohousePost [" + (brief != null ? "brief=" + brief + ", " : "")
				+ (categories != null ? "categories=" + categories + ", " : "")
				+ (duration != null ? "duration=" + duration + ", " : "")
				+ (embed != null ? "embed=" + embed + ", " : "") + (images != null ? "images=" + images + ", " : "")
				+ (title != null ? "title=" + title + ", " : "") + (url != null ? "url=" + url : "") + "]";
	}
}