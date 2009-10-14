package migool.poster.cms.ucoz;

import static migool.poster.cms.ucoz.IUcozConstants.*;
import static migool.util.HtmlParserUtil.*;

import java.util.ArrayList;
import java.util.List;

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
		NodeList scripts = (new Parser(html)).extractAllNodesThatMatch(new TagNameFilter(SCRIPT));
		int size = scripts.size();
		String script = null;
		for (int i = 0; i < size; i++) {
			script = ((ScriptTag) scripts.elementAt(i)).getChildrenHTML();
			if (EmptyChecker.isNotNullOrEmpty(script) && script.contains("num>=")) {
				return Integer.parseInt((new Regex(script, "(?<=num\\>\\=)[0-9]+").getMatches()[0][0]));
			}
		}
		return -1;
	}
}
