package migool.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.TextareaTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class HtmlParserUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private HtmlParserUtil() {
	}

	public static final String OBJECT = "object";
	public static final String TEXTAREA = "textarea";
	public static final String IMG = "img";
	public static final String TITLE = "title";
	public static final String LI = "li";
	public static final String DIV = "div";
	public static final String SPAN = "span";
	public static final String VAR = "var";
	public static final String HIDDEN = "hidden";
	public static final String FORM = "form";
	public static final String PASSWORD = "password";
	public static final String TYPE = "type";
	public static final String INPUT = "input";
	public static final String SELECT = "select";
	public static final String VALUE = "value";
	public static final String NAME = "name";
	public static final String CLASS = "class";
	public static final String CHECKBOX = "checkbox";
	public static final String RADIO = "radio";
	public static final String LABEL = "label";
	public static final String FOR = "for";
	public static final String ID = "id";
	public static final String SCRIPT = "script";
	public static final String A = "a";
	public static final String B = "b";
	public static final String FILE = "file";
	public static final String STRONG = "strong";
	public static final String H1 = "h1";
	public static final String H2 = "h2";
	public static final String H3 = "h3";
	public static final String H4 = "h4";
	public static final String H5 = "h5";
	public static final String H6 = "h6";

	public static final String TABLE = "table";
	public static final String TR = "tr";
	public static final String TD = "td";

	public static final String SELECTED = "selected";
	public static final String CHECKED = "checked";

	public static final NodeFilter TYPE_HIDDEN_FILTER = new HasAttributeFilter(TYPE, HIDDEN);
	public static final NodeFilter TYPE_FILE_FILTER = new HasAttributeFilter(TYPE, FILE);
	public static final NodeFilter TYPE_PASSWORD_FILTER = new HasAttributeFilter(TYPE, PASSWORD);

	public static final NodeFilter SCRIPT_FILTER = new TagNameFilter(SCRIPT);

	public static final NodeFilter INPUT_FILTER = new TagNameFilter(INPUT);
	public static final NodeFilter FILE_INPUT_FILTER = new AndFilter(INPUT_FILTER, TYPE_FILE_FILTER);
	public static final NodeFilter PASSWORD_INPUT_FILTER = new AndFilter(INPUT_FILTER, TYPE_PASSWORD_FILTER);
	public static final NodeFilter HIDDEN_INPUT_FILTER = new AndFilter(INPUT_FILTER, TYPE_HIDDEN_FILTER);
	public static final NodeFilter NOT_HIDDEN_INPUT_FILTER = new NotFilter(HIDDEN_INPUT_FILTER);

	public static final NodeFilter A_FILTER = new TagNameFilter(A);
	public static final NodeFilter B_FILTER = new TagNameFilter(B);
	public static final NodeFilter IMG_FILTER = new TagNameFilter(IMG);
	public static final NodeFilter TEXTAREA_FILTER = new TagNameFilter(TEXTAREA);
	public static final NodeFilter SELECT_FILTER = new TagNameFilter(SELECT);
	public static final NodeFilter OBJECT_FILTER = new TagNameFilter(OBJECT);
	public static final NodeFilter DIV_FILTER = new TagNameFilter(DIV);
	public static final NodeFilter SPAN_FILTER = new TagNameFilter(SPAN);
	public static final NodeFilter LABEL_FILTER = new TagNameFilter(LABEL);
	public static final NodeFilter STRONG_FILTER = new TagNameFilter(STRONG);
	public static final NodeFilter H1_FILTER = new TagNameFilter(H1);
	public static final NodeFilter H2_FILTER = new TagNameFilter(H2);
	public static final NodeFilter H3_FILTER = new TagNameFilter(H3);
	public static final NodeFilter H4_FILTER = new TagNameFilter(H4);
	public static final NodeFilter H5_FILTER = new TagNameFilter(H5);
	public static final NodeFilter H6_FILTER = new TagNameFilter(H6);

	public static final NodeFilter TABLE_FILTER = new TagNameFilter(TABLE);
	public static final NodeFilter TR_FILTER = new TagNameFilter(TR);
	public static final NodeFilter TD_FILTER = new TagNameFilter(TD);

	public static final NodeFilter FORM_FILTER = new TagNameFilter(FORM);
	public static final NodeFilter LOGIN_FORM_FILTER = new AndFilter(FORM_FILTER, new HasChildFilter(
			PASSWORD_INPUT_FILTER, true));
	public static final NodeFilter FILE_FORM_FILTER = new AndFilter(FORM_FILTER, new HasChildFilter(FILE_INPUT_FILTER,
			true));

	/**
	 * Link that contains image
	 */
	public static final NodeFilter A_IMG_FILTER = new AndFilter(A_FILTER, new HasChildFilter(IMG_FILTER));

	public static final NodeFilter TEXT_NODE_FILTER = new NodeClassFilter(TextNode.class);

	/**
	 * 
	 * @param html
	 * @param filter
	 * @return
	 * @throws ParserException
	 */
	public static FormTag getForm(final String html, final NodeFilter filter) throws ParserException {
		final NodeList nl = (new Parser(html)).parse(filter);
		if (nl.size() > 0) {
			return (FormTag) nl.elementAt(0);
		}
		throw new ParserException("Unable to find form");
	}

	/**
	 * Searches form which contains password input and returns it.
	 * 
	 * @param html
	 * @return
	 * @throws ParserException
	 */
	public static FormTag getLoginForm(final String html) throws ParserException {
		return getForm(html, LOGIN_FORM_FILTER);
	}

	/**
	 * 
	 * @param html
	 * @return
	 * @throws ParserException
	 */
	public static FormTag getFileForm(final String html) throws ParserException {
		return getForm(html, FILE_FORM_FILTER);
	}

	/**
	 * 
	 * @param form
	 * @param names
	 * @return
	 */
	public static InputTag getInputTag(final FormTag form, final String[] names) {
		InputTag ret;
		for (final String name : names) {
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
	 * @param params
	 * @return
	 */
	public static Map<String, String> setForm(final FormTag form, final Map<String, String> params) {
		final Map<String, String> ret = (params == null) ? new LinkedHashMap<String, String>() : params;

		setInputs(form.getFormInputs(), params);
		// setInputs(form.getChildren().extractAllNodesThatMatch(INPUT_FILTER,
		// true), params);
		setTextareas(form.getFormTextareas(), params);
		setSelects(getSelects(form), params);

		return ret;
	}

	/**
	 * 
	 * @param form
	 * @param params
	 */
	public static void setInputs(final FormTag form, final Map<String, String> params) {
		setInputs(form.getFormInputs(), params);
	}

	/**
	 * 
	 * @param inputs
	 * @param params
	 */
	public static void setInputs(final NodeList inputs, final Map<String, String> params) {
		final int inputsSize = inputs.size();
		for (int i = 0; i < inputsSize; i++) {
			final InputTag input = (InputTag) inputs.elementAt(i);
			setInput(input, params);
		}
	}

	public static void setInput(final InputTag input, final Map<String, String> params) {
		final String name = input.getAttribute(NAME);
		if (name != null) {
			if (isCheckbox(input) && !isChecked(input)) {
				return;
			}
			// final String value = ret.containsKey(name) ? ret.get(name) :
			// input.getAttribute(VALUE);
			final String value = input.getAttribute(VALUE);
			if (value != null) {
				params.put(name, value);
			}
		}
	}

	public static boolean isCheckbox(final InputTag input) {
		final String value = input.getAttribute(TYPE);
		return CHECKBOX.equalsIgnoreCase(value);
	}

	public static boolean isChecked(final InputTag input) {
		boolean ret = false;
		final Attribute attribute = input.getAttributeEx(CHECKED);
		if (attribute != null) {
			final String value = attribute.getValue();
			ret = (value == null) || CHECKED.equalsIgnoreCase(value);
		}
		return ret;
	}

	/**
	 * 
	 * @param inputs
	 * @param params
	 */
	public static void setInputs(final NodeList inputs, final List<NameValuePair> params) {
		final int size = inputs.size();
		for (int i = 0; i < size; i++) {
			final InputTag input = (InputTag) inputs.elementAt(i);
			params.add(new BasicNameValuePair(input.getAttribute(NAME), input.getAttribute(VALUE)));
		}
	}

	public static void setInputs(final NodeList inputs, final MultipartEntity entity) {
		final int inputsSize = inputs.size();
		for (int i = 0; i < inputsSize; i++) {
			final InputTag input = (InputTag) inputs.elementAt(i);
			try {
				entity.addPart(input.getAttribute(NAME), new StringBody(input.getAttribute(VALUE)));
			} catch (final UnsupportedEncodingException e) {
			}
		}
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static NodeList getHiddenInputs(final FormTag form) {
		return form.getFormInputs().extractAllNodesThatMatch(
				new AndFilter(new TagNameFilter(INPUT), new HasAttributeFilter(TYPE, HIDDEN)), true);
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static NodeList getNotHiddenInputs(final FormTag form) {
		return form.getFormInputs().extractAllNodesThatMatch(
				new AndFilter(new TagNameFilter(INPUT), new NotFilter(new HasAttributeFilter(TYPE, HIDDEN))), true);
	}

	/**
	 * 
	 * @param form
	 * @param params
	 */
	public static void setHiddenInputs(final FormTag form, final List<NameValuePair> params) {
		setInputs(getHiddenInputs(form), params);
	}

	/**
	 * 
	 * @param form
	 * @param params
	 */
	public static void setHiddenInputs(final FormTag form, final Map<String, String> params) {
		setInputs(getHiddenInputs(form), params);
	}

	/**
	 * 
	 * @param form
	 * @param entity
	 */
	public static void setHiddenInputs(final FormTag form, final MultipartEntity entity) {
		setInputs(getHiddenInputs(form), entity);
	}

	/**
	 * 
	 * @param textareas
	 * @param params
	 */
	public static void setTextareas(final NodeList textareas, final Map<String, String> params) {
		final int textareasSize = textareas.size();
		for (int i = 0; i < textareasSize; i++) {
			final TextareaTag textarea = (TextareaTag) textareas.elementAt(i);
			final String name = textarea.getAttribute(NAME);
			// final String value = params.containsKey(name) ? params.get(name)
			// : textarea.getValue();
			final String value = textarea.getValue();
			params.put(name, value);
		}
	}

	public static NodeList getSelects(final FormTag form) {
		return form.getChildren().extractAllNodesThatMatch(SELECT_FILTER, true);
	}

	public static void setSelects(final NodeList selects, final Map<String, String> params) {
		final int selectsSize = selects.size();
		for (int i = 0; i < selectsSize; i++) {
			final SelectTag select = (SelectTag) selects.elementAt(i);
			boolean selected = false;
			for (final OptionTag option : select.getOptionTags()) {
				if (isOptionSelected(option)) {
					selected = true;
					final String name = select.getAttribute(NAME);
					final String value = option.getValue();
					params.put(name, value);
					break;
				}
			}
			if (!selected) {
				final OptionTag option = select.getOptionTags()[0];
				final String name = select.getAttribute(NAME);
				final String value = option.getValue();
				params.put(name, value);
			}
		}
	}

	public static boolean isOptionSelected(final OptionTag option) {
		// option.getAttribute(SELECTED);
		boolean ret = false;
		final Attribute attribute = option.getAttributeEx(SELECTED);
		if (attribute != null) {
			final String value = attribute.getValue();
			ret = (value == null) || (SELECTED.equalsIgnoreCase(value));
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, String> getSelectOptions(final SelectTag select) {
		final OptionTag[] options = select.getOptionTags();
		final Map<String, String> ret = new TreeMap<String, String>();
		for (final OptionTag option : options) {
			ret.put(option.getValue(), option.getOptionText());
		}
		return ret;
	}

	/**
	 * 
	 * @param node
	 * @param tags
	 * @return
	 */
	public static NodeList getChildTags(final Node node, final List<String> tagNames) {
		final NodeList children = node.getChildren();
		if (children != null) {
			final int tagNamesSize = tagNames.size();
			final List<NodeFilter> predicates = new ArrayList<NodeFilter>(tagNamesSize);
			for (final String name : tagNames) {
				predicates.add(new TagNameFilter(name));
			}
			return children.extractAllNodesThatMatch(new OrFilter(predicates.toArray(new NodeFilter[tagNamesSize])),
					true);
		}
		return null;
	}

	/**
	 * 
	 * @param nl
	 * @return
	 */
	public static List<String> getNameAttributeValues(final NodeList nl) {
		final int nlSize = nl.size();
		final List<String> ret = new ArrayList<String>(nlSize);
		String name = null;
		for (int i = 0; i < nlSize; i++) {
			if ((name = ((TagNode) nl.elementAt(i)).getAttribute(NAME)) != null) {
				ret.add(name);
			}
		}
		return ret;
	}

	/**
	 * 
	 */
	public static List<NameValuePair> toListNameValuePair(final Map<String, String> params) {
		final Set<String> names = params.keySet();
		final List<NameValuePair> ret = new ArrayList<NameValuePair>(names.size());
		for (final String name : names) {
			final String value = params.get(name);
			if (value != null) {
				ret.add(new BasicNameValuePair(name, params.get(name)));
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param params
	 * @param form
	 * @param name
	 * @param value
	 */
	public static void fillTextArea(final Map<String, String> params, final FormTag form, final String name,
			final String value) {
		if (form.getTextAreaTag(name) != null && StringUtil.isNotNullOrEmpty(value)) {
			params.put(name, value);
		}
	}

	/**
	 * 
	 * @param params
	 * @param form
	 * @param name
	 * @param value
	 */
	public static void fillInputText(final Map<String, String> params, final FormTag form, final String name,
			final String value) {
		if (form.getInputTag(name) != null && StringUtil.isNotNullOrEmpty(value)) {
			params.put(name, value);
		}
	}

	/**
	 * 
	 * @param params
	 * @param form
	 * @param name
	 * @param value
	 */
	public static void fillInputCheckbox(final Map<String, String> params, final FormTag form, final String name,
			final boolean value) {
		if (form.getInputTag(name) != null) {
			params.put(name, value ? "1" : "0");
		}
	}

	/**
	 * 
	 * @param entity
	 * @param params
	 * @throws UnsupportedEncodingException
	 */
	public static void fillParams(final MultipartEntity entity, final Map<String, String> params)
			throws UnsupportedEncodingException {
		for (final String name : params.keySet()) {
			// if (name != null) {
			entity.addPart(name, new StringBody(params.get(name)));
			// }
		}
	}

	/**
	 * 
	 * @param select
	 * @return
	 */
	public static boolean isMultiple(final SelectTag select) {
		// TODO
		return false;
	}

	/**
	 * 
	 * @param select
	 * @return
	 */
	public static Map<String, String> getOptionsValues(final SelectTag select) {
		final Map<String, String> ret = new HashMap<String, String>();
		for (final OptionTag option : select.getOptionTags()) {
			ret.put(option.getValue(), option.getOptionText());
		}
		return ret;
	}

	public static Map<String, String> setForm(final FormTag form) {
		return setForm(form, null);
	}

	public static String removeRemarks(final String html) {
		// TODO
		return null;
	}

	public static String htmlStringToString(final String htmlString) {
		// TODO http://www.rgagnon.com/javadetails/java-0306.html
		String ret = htmlString;
		ret = ret.replace("&nbsp;", " ");
		return ret;
	}

	public static String toText(final NodeList nl) {
		final StringBuilder ret = new StringBuilder();
		final int size = nl.size();
		for (int i = 0; i < size; i++) {
			final Node node = nl.elementAt(i);
			if (node instanceof TextNode) {
				final String text = ((TextNode) node).getText();
				if (!"".equals(text.trim())) {
					ret.append(text);
				}
			} else if (node instanceof TagNode) {
				final String tag = ((TagNode) node).getTagName().toLowerCase();
				if ("p".equals(tag)) {
					ret.append(StringUtil.LINE_SEPARATOR);
					ret.append(StringUtil.LINE_SEPARATOR);
				} else if ("br".equals(tag)) {
					ret.append(StringUtil.LINE_SEPARATOR);
				}
			}
			final NodeList children = node.getChildren();
			if (children != null && children.size() > 0) {
				ret.append(toText(children));
			}
		}
		return ret.toString();
	}
}
