package migool.post;

import java.util.List;

import migool.post.category.Category;
import migool.post.internal.FileLink;
import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public class Post {
	public String title;
	public String url;
	public List<Category> categories;
	public Image image;

	public String begStory;
	public String endStory;

	public String name;
	public String originalName;
	public String version;
	public String year;
	public String format;
	public String language;
	public String size;
	public List<Image> screens;
	public List<List<FileLink>> fileLinks;
	public String tags;

	// Warez
	public String developer;
	public String os;
	public String platform; // 32/64
	public boolean free;
	public boolean crack;
}