package migool.post.category;

import java.util.List;

/**
 * 
 * @author Denis Migol
 *
 */
public class Category {

	public final String name;

	public List<String> synonyms;
	public Category owner;
	public List<Category> children;

	/**
	 * 
	 * @param name
	 */
	public Category(String name) {
		this.name = name;
	}
}
