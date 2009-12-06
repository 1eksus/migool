package migool.post.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Categories {
	public static final List<Category> CATS;

	/* Main categories */
	public static final Category SOFT = createSoft();
	public static final Category SCRIPTS = createScripts();
	public static final Category DRIVERS = new Category("drivers");
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

	/**
	 * 
	 * @return
	 */
	private static final Category createSoft() {
		Category ret = new Category("soft");
		ret.synonyms = Arrays.asList(new String[]{"soft", "софт", "programs", "программы", "progs", "проги"});
		//ret.children.add();
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	private static Category createScripts() {
		Category ret = new Category("scripts");
		return ret;
	}

	/**
	 * 
	 */
	static {
		CATS = new ArrayList<Category>();
		CATS.add(SOFT);
	}
}
