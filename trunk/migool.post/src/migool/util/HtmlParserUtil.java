package migool.util;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * @author Denis Migol
 *
 */
public final class HtmlParserUtil {
	private HtmlParserUtil() {
	}

	/**
	 * Searches form which contains password input and returns it.
	 * @param html
	 * @return
	 */
	public static FormTag getLoginForm(String html) {
		Parser parser;
		try {
			parser = new Parser(html);
			NodeList nl = parser.parse(new AndFilter(new TagNameFilter("form"), new HasChildFilter(new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("type", "password")), true)));
			if (nl.size() == 1) {
				return (FormTag) nl.elementAt(0);
			}
			return null;
		} catch (ParserException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param form
	 * @param names
	 * @return
	 */
	public static InputTag getInputTag(FormTag form, String[] names) {
		InputTag ret;
		for (String name : names) {
			ret = form.getInputTag(name);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static NodeList getHiddenInputs(FormTag form) {
		return form.getFormInputs().extractAllNodesThatMatch(new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("type", "hidden")), true);
	}	
}
