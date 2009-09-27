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
	public static final Category VIDEO = new Category("video");
	public static final Category MUSIC = new Category("music");
	public static final Category GAMES = new Category("games");
	public static final Category BOOKS = new Category("books");
	public static final Category JOURNALS = new Category("journals");
	public static final Category MOBILE = new Category("mobile");
	public static final Category PSP = new Category("psp");
	public static final Category XXX = new Category("xxx");
	public static final Category EROTIC = new Category("erotic");
	//public static final Category ANIME = new Category("anime");
	public static final Category NEWS = new Category("news");
	public static final Category HUMOUR = new Category("humour");

	static {
		CATS = new ArrayList<Category>();
		CATS.add(SOFT);
	}
}
