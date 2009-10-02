package migool.poster.cms.ucoz;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IUcozConstants {
	
	public static final String[] UCOZ_DOMAINS = { "ucoz.ru", "vo.uz", "at.ua", "p0.ru",
		"3dn.ru", "my1.ru", "clan.su", "moy.su", "do.am", "pp.net.ua",
		"ucoz.ua", "ucoz.kz", "ucoz.lv", "ucoz.com", "ucoz.net",
		"ucoz.org", "ucoz.co.uk", "ucoz.de", "ws.co.ua" };
	
	public static final String LOGIN_POST_PATH = "/index/sub";

	public static final String NEWS_PATH = "/news";
	public static final String LOAD_PATH = "/load";
	public static final String PUBL_PATH = "/publ";
	public static final String BLOG_PATH = "/blog";
	public static final String PHOTO_PATH = "/photo";
	public static final String DIR_PATH = "/dir";
	public static final String BOARD_PATH = "/board";
	public static final String GB_PATH = "/gb";
	public static final String FAQ_PATH = "/faq";

	public static final String ADD_PATH = "/0-0-0-0-1";

	public static final String NEWS_ADD_PATH = NEWS_PATH + ADD_PATH;
	public static final String LOAD_ADD_PATH = LOAD_PATH + ADD_PATH;
	public static final String PUBL_ADD_PATH = PUBL_PATH + ADD_PATH;
	public static final String BLOG_ADD_PATH = BLOG_PATH + ADD_PATH;
	public static final String PHOTO_ADD_PATH = PHOTO_PATH + ADD_PATH;
	public static final String DIR_ADD_PATH = DIR_PATH + ADD_PATH;
	public static final String BOARD_ADD_PATH = BOARD_PATH + ADD_PATH;
	public static final String GB_ADD_PATH = GB_PATH + ADD_PATH;
	public static final String FAQ_ADD_PATH = FAQ_PATH + ADD_PATH;
	
	public static final String RSS_PATH = "/rss";
	
	public static final String NEWS_RSS_PATH = NEWS_PATH + RSS_PATH;
	public static final String LOAD_RSS_PATH = LOAD_PATH + RSS_PATH;
	public static final String PUBL_RSS_PATH = PUBL_PATH + RSS_PATH;
	public static final String BLOG_RSS_PATH = BLOG_PATH + RSS_PATH;
	//public static final String PHOTO_RSS_PATH = PHOTO_PATH + RSS_PATH;
	public static final String DIR_RSS_PATH = DIR_PATH + RSS_PATH;
	public static final String BOARD_RSS_PATH = BOARD_PATH + RSS_PATH;
	//public static final String GB_RSS_PATH = GB_PATH + RSS_PATH;
	//public static final String FAQ_RSS_PATH = FAQ_PATH + RSS_PATH;
	
	// Name of HTML tags for adding materials:

	public static final String CAT = "cat";
	public static final String TITLE = "title";
	public static final String BRIEF = "brief";
	//public static final String FORMAT_BRIEF = "format_brief";
	public static final String MESSAGE = "message";
	//public static final String FORMAT_MESSAGE = "format_message";
	// !!!
	public static final String FILE = "file"; // file1..?
	//public static final String FILE1 = "file1";
	//public static final String SHOW_ATTACHMENTS = "show_attachments";
	// !!!
	public static final String OTHER = "other"; // OTHER1..OTHER5
	//public static final String SBCR = "sbcr";
}
