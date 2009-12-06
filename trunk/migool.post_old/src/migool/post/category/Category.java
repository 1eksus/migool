package migool.post.category;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Category {

	public final String name;
	public final Category owner;

	public List<String> synonyms = new ArrayList<String>();
	public List<Category> children = new ArrayList<Category>();

	/**
	 * 
	 * @param name
	 */
	public Category(String name) {
		this(name, null);
	}

	/**
	 * 
	 * @param name
	 */
	public Category(String name, Category owner) {
		this(name, owner, new ArrayList<String>(), new ArrayList<Category>());
	}

	/**
	 * @param name
	 * @param synonyms
	 * @param owner
	 * @param children
	 */
	public Category(String name, Category owner, List<String> synonyms, List<Category> children) {
		this.name = name;
		this.synonyms = synonyms;
		this.owner = owner;
		this.children = children;
	}
}
