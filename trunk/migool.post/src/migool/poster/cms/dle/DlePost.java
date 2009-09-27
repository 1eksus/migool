package migool.poster.cms.dle;

import java.util.List;
import java.util.Properties;

import migool.post.category.Category;

/**
 * 
 * @author Denis Migol
 *
 */
public class DlePost {
	public String title;
	public String url;

	public List<Category> categories;

	public String shortStory;
	public String fullStory;

	public List<String> tags;
	
	public Properties inputs;
}