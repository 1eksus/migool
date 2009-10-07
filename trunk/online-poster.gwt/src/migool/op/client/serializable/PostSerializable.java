package migool.op.client.serializable;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public class PostSerializable implements Serializable {
	public String title;
	public String url;
	public List<String> categories;
	//public Image image; // TODO

	public String begStory;
	public String endStory;

	public String name;
	public String originalName;
	public String version;
	public String year;
	public String format;
	public String language;
	public String size;
	//public List<Image> screens; // TODO
	public String fileLinks;
	public String tags;

	// Warez
	public String developer;
	public String os;
	public String platform; // 32/64
	public boolean free;
	public boolean crack;
}
