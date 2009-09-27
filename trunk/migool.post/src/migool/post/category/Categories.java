package migool.post.category;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Categories {
	public static final List<Category> CATS;

	/* Main categories */
	public static final Category SOFT = new Category("soft");
	public static final Category FILMS = new Category("films");
	public static final Category MUSIC = new Category("music");
	public static final Category GAMES = new Category("games");
	public static final Category BOOKS = new Category("books");
	public static final Category JOURNALS = new Category("journals");
	public static final Category MOBILE = new Category("mobile");

	static {
		CATS = new ArrayList<Category>();
		CATS.add(SOFT);
	}
}
