package migool.poster.cms.dle;

import java.util.List;
import java.util.Properties;

import migool.post.category.Category;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IDlePost {
	String getTitle();
	String getUrl();
	
	List<Category> getCategories();

	String getShortStory();
	String getFullStory();

	List<String> getTags();

	Properties getInputs();
}
