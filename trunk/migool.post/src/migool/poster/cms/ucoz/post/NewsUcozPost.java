package migool.poster.cms.ucoz.post;

import java.util.List;

/**
 * 
 * @author Denis Migol
 *
 */
public class NewsUcozPost {

	// constants:

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

	// fields:
	public String cat;
	public String title;
	public String brief;
	public String message;
	public List<String> files;
	public List<String> others;
}
