package migool.poster.cms.ucoz;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface IUcozConstants {

	public static final String[] UCOZ_DOMAINS = { "ucoz.ru", "vo.uz", "at.ua", "p0.ru", "3dn.ru", "my1.ru", "clan.su",
			"moy.su", "do.am", "pp.net.ua", "ucoz.ua", "ucoz.kz", "ucoz.lv", "ucoz.com", "ucoz.net", "ucoz.org",
			"ucoz.co.uk", "ucoz.de", "ws.co.ua" };

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
	// public static final String PHOTO_RSS_PATH = PHOTO_PATH + RSS_PATH;
	public static final String DIR_RSS_PATH = DIR_PATH + RSS_PATH;
	public static final String BOARD_RSS_PATH = BOARD_PATH + RSS_PATH;
	// public static final String GB_RSS_PATH = GB_PATH + RSS_PATH;
	// public static final String FAQ_RSS_PATH = FAQ_PATH + RSS_PATH;

	// Name of HTML tags for adding materials:

	public static final String _1 = "1";
	public static final String _2 = "2";
	public static final String _3 = "3";
	public static final String _4 = "4";
	public static final String _5 = "5";
	public static final String _6 = "6";
	public static final String _7 = "7";
	public static final String _8 = "8";
	public static final String _9 = "9";
	public static final String _10 = "10";
	public static final String _11 = "11";
	public static final String _12 = "12";
	public static final String _13 = "13";
	public static final String _14 = "14";
	public static final String _15 = "15";
	public static final String _16 = "16";
	public static final String _17 = "17";
	public static final String _18 = "18";
	public static final String _19 = "19";
	public static final String _20 = "20";

	public static final String CAT = "cat";
	public static final String TITLE = "title";
	public static final String BRIEF = "brief";
	public static final String FORMAT_BRIEF = "format_brief";
	public static final String HTML_BRIEF = "html_brief";
	public static final String MESSAGE = "message";
	public static final String FORMAT_MESSAGE = "format_message";
	public static final String HTML_MESSAGE = "html_message";
	// !!!
	public static final String FILE = "file"; // file1..?
	public static final String FILE1 = FILE + _1;
	public static final String FILE2 = FILE + _2;
	public static final String FILE3 = FILE + _3;
	public static final String FILE4 = FILE + _4;
	public static final String FILE5 = FILE + _5;
	public static final String FILE6 = FILE + _6;
	public static final String FILE7 = FILE + _7;
	public static final String FILE8 = FILE + _8;
	public static final String FILE9 = FILE + _9;
	public static final String FILE10 = FILE + _10;
	public static final String FILE11 = FILE + _11;
	public static final String FILE12 = FILE + _12;
	public static final String FILE13 = FILE + _13;
	public static final String FILE14 = FILE + _14;
	public static final String FILE15 = FILE + _15;
	public static final String FILE16 = FILE + _16;
	public static final String FILE17 = FILE + _17;
	public static final String FILE18 = FILE + _18;
	public static final String FILE19 = FILE + _19;
	public static final String FILE20 = FILE + _20;
	public static final String[] FILES = new String[] { FILE1, FILE2, FILE3, FILE4, FILE5, FILE6, FILE7, FILE8, FILE9,
			FILE10, FILE11, FILE12, FILE13, FILE14, FILE15, FILE16, FILE17, FILE18, FILE19, FILE20 };
	// public static final String SHOW_ATTACHMENTS = "show_attachments";
	// !!!
	public static final String OTHER = "other"; // OTHER1..OTHER5
	public static final String OTHER1 = OTHER + _1;
	public static final String OTHER2 = OTHER + _2;
	public static final String OTHER3 = OTHER + _3;
	public static final String OTHER4 = OTHER + _4;
	public static final String OTHER5 = OTHER + _5;
	// public static final String SBCR = "sbcr";

	public static final String FILTER3 = "filter3"; // board kind of board

	public static final String OCAT = "ocat";
	public static final String ANAME = "aname";
	public static final String AEMAIL = "aemail";
	public static final String ASITE = "asite";
	public static final String SOURCE = "source";

	public static final String SLINK = "slink";

	public static final String PHONE = "phone";

	public static final String PYA = "pya";
	public static final String PMA = "pma";
	public static final String PDA = "pda";

	public static final String SUBJECT = "subject";
	public static final String NAME = "name";
	public static final String NAME1 = NAME + _1;

	public static final String ICQ = "icq";
	public static final String COUNTRY = "country";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String CODE = "code"; // CAPTCHA

	public static final String PHOTO = "photo"; // photo1..?
	public static final String PHOTO1 = PHOTO + _1;

	public static final String DESCRIPTION = "description"; // description1..?
	public static final String DESCRIPTION1 = DESCRIPTION + _1;

	public static final String IS_PENDING = "is_pending";
	public static final String COMS_ALLOWED = "coms_allowed";
}
