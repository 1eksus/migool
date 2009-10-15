package migool.poster.cms.ucoz;

import static migool.poster.cms.ucoz.IUcozConstants.*;
import static migool.util.HtmlParserUtil.*;

import java.util.ArrayList;
import java.util.List;

import migool.host.auth.LoginResponse;
import migool.util.EmptyChecker;
import migool.util.Regex;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.LabelTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class UcozUtil {
	private UcozUtil() {
	}

	private static final NodeFilter CATEGORIES_FILTER = new AndFilter(new TagNameFilter(INPUT), new HasAttributeFilter(
			NAME, OCAT));

	/**
	 * 
	 * @author Denis Migol
	 * 
	 */
	static class CategoryEntity {
		public final String value;
		public final String title;
		public final boolean radio;

		/**
		 * 
		 * @param value
		 * @param title
		 */
		public CategoryEntity(String value, String title) {
			this(value, title, true);
		}

		/**
		 * 
		 * @param value
		 * @param title
		 * @param radio
		 */
		public CategoryEntity(String value, String title, boolean radio) {
			this.value = value;
			this.title = title;
			this.radio = radio;
		}
	}

	/**
	 * 
	 * @param forId
	 * @return
	 * @throws ParserException
	 */
	private static final String getLabelText(String html, String forId) throws ParserException {
		LabelTag label = (LabelTag) (new Parser(html)).extractAllNodesThatMatch(
				new AndFilter(new TagNameFilter(LABEL), new HasAttributeFilter(FOR, forId))).elementAt(0);
		return label.getStringText();
	}

	/**
	 * 
	 * @param html
	 * @return
	 * @throws ParserException
	 */
	public static final List<CategoryEntity> getCategories(String html) throws ParserException {
		NodeList nl = (new Parser(html)).extractAllNodesThatMatch(CATEGORIES_FILTER);

		String value = null;
		String title = null;
		boolean radio = false;
		int size = nl.size();
		ArrayList<CategoryEntity> ret = new ArrayList<CategoryEntity>(size);
		for (int i = 0; i < size; i++) {
			InputTag ocat = (InputTag) nl.elementAt(i);
			value = ocat.getAttribute(VALUE);
			title = getLabelText(html, ocat.getAttribute(ID));
			radio = (RADIO.equals(ocat.getAttribute(TYPE))) ? true : false;
			ret.add(new CategoryEntity(value, title, radio));
		}
		return ret;
	}

	/**
	 * 
	 * @param html
	 * @return
	 * @throws ParserException
	 */
	public static final int getMaxFilesPublPost(String html) throws ParserException {
		NodeList scripts = (new Parser(html)).extractAllNodesThatMatch(SCRIPT_FILTER);
		int size = scripts.size();
		String script = null;
		for (int i = 0; i < size; i++) {
			script = ((ScriptTag) scripts.elementAt(i)).getChildrenHTML();
			if (EmptyChecker.isNotNullOrEmpty(script) && script.contains("num<")) {
				return Integer.parseInt((new Regex(script, "(?<=num\\<)[0-9]+").getMatches()[0][0]));
			}
		}
		return 1;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static final String _dC(String s) {
		String r = "";
		int l = s.length() - 1;
		int k = Character.digit(s.charAt(l), 10);
		for (int i = 0; i < l; i++) {
			int c = s.charAt(i) - k;
			if (c < 32) {
				c = (127 - (32 - c));
			}
			r += (char) c;
		}
		return r;
		// <input type="hidden" name="rgh3399178859" value="525752">
		// <input type="hidden" name="ewh3399178859" value="897993">
	}

	/**
	 * 
	 * @return
	 */
	public static final LoginResponse checkLogin(String html) {
		if (getLoginForm(html) != null) {
			return new LoginResponse(LoginResponse.ERROR);
		}
		String[] add_pathes = new String[] { NEWS_ADD_PATH, LOAD_ADD_PATH, PUBL_ADD_PATH, BLOG_ADD_PATH,
				PHOTO_ADD_PATH, DIR_ADD_PATH, BOARD_ADD_PATH, GB_ADD_PATH, FAQ_ADD_PATH };
		for (String path : add_pathes) {
			if (html.contains(path)) {
				return new LoginResponse(LoginResponse.OK);
			}
		}
		return new LoginResponse(LoginResponse.ERROR);
	}
}
