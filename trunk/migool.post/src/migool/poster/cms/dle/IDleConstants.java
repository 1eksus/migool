package migool.poster.cms.dle;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IDleConstants {
	public static final String REGISTER_PATH = "/index.php?do=register";
	public static final String ADD_NEWS_PATH = "/index.php?do=addnews";
	public static final String UPLOAD_PATH = "/engine/images.php?area=short_story&add_id=";
	
	public static String[] LOGIN_INPUTS = { "login_name", "login" };
	public static String[] PASS_INPUTS = { "login_password", "pass" };
	
//	public static final String LOGIN_NAME = "login_name";
//	public static final String LOGIN_PASSWORD = "login_password";
	public static final String LOGIN_SUBMIT = "login=submit";

	public static final String ENTRYFORM = "entryform";
	public static final String CATLIST = "catlist[]";
	public static final String SHORT_STORY = "short_story";
	public static final String FULL_STORY = "full_story";
	public static final String ADD = "add";
	public static final String MOD = "mod";
}
