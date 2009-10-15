package migool.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * @author Denis Migol
 *
 */
public final class HtmlParserUtil {

	public static final String HIDDEN = "hidden";
	public static final String FORM = "form";
	public static final String PASSWORD = "password";
	public static final String TYPE = "type";
	public static final String INPUT = "input";
	public static final String VALUE = "value";
	public static final String NAME = "name";
	public static final String CLASS = "class";
	public static final String CHECKBOX = "checkbox";
	public static final String RADIO = "radio";
	public static final String LABEL = "label";
	public static final String FOR = "for";
	public static final String ID = "id";
	public static final String SCRIPT = "script";

	public static final NodeFilter PASSWORD_INPUT_FILTER = new AndFilter(new TagNameFilter(INPUT), new HasAttributeFilter(TYPE, PASSWORD));
	public static final NodeFilter SCRIPT_FILTER = new TagNameFilter(SCRIPT);

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
			NodeList nl = parser.parse(new AndFilter(new TagNameFilter(FORM), new HasChildFilter(PASSWORD_INPUT_FILTER, true)));
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
	 * @param inputs
	 * @param params
	 */
	public static void setInputs(NodeList inputs, List<NameValuePair> params) {
		InputTag input = null;
		for (int i = 0; i < inputs.size(); i++) {
			input = (InputTag) inputs.elementAt(i);
			params.add(new BasicNameValuePair(input.getAttribute(NAME), input.getAttribute(VALUE)));
		}		
	}

	/**
	 * 
	 * @param inputs
	 * @param params
	 */
	public static void setInputs(NodeList inputs, Map<String, String> params) {
		InputTag input = null;
		String name = null;
		String value = null;
		for (int i = 0; i < inputs.size(); i++) {
			input = (InputTag) inputs.elementAt(i);
			name = input.getAttribute(NAME);
			if (name != null) {
				value = input.getAttribute(VALUE);
				if (value != null) {
					params.put(name, value);
				}
			}
		}
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static NodeList getHiddenInputs(FormTag form) {
		return form.getFormInputs().extractAllNodesThatMatch(new AndFilter(new TagNameFilter(INPUT), new HasAttributeFilter(TYPE, HIDDEN)), true);
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static NodeList getNotHiddenInputs(FormTag form) {
		return form.getFormInputs().extractAllNodesThatMatch(new AndFilter(new TagNameFilter(INPUT), new NotFilter(new HasAttributeFilter(TYPE, HIDDEN))), true);
	}

	/**
	 * 
	 * @param form
	 * @param params
	 */
	public static void setHiddenInputs(FormTag form, List<NameValuePair> params) {
		setInputs(getHiddenInputs(form), params);
	}

	/**
	 * 
	 * @param form
	 * @param params
	 */
	public static void setHiddenInputs(FormTag form, Map<String, String> params) {
		setInputs(getHiddenInputs(form), params);
	}

	/**
	 * 
	 * @param form
	 * @param entity
	 */
	public static void setHiddenInputs(FormTag form, MultipartEntity entity) {
		NodeList hiddens = getHiddenInputs(form);
		InputTag input = null;
		for (int i = 0; i < hiddens.size(); i++) {
			input = (InputTag) hiddens.elementAt(i);
			try {
				entity.addPart(input.getAttribute(NAME), new StringBody(input.getAttribute(VALUE)));
			} catch (UnsupportedEncodingException e) {
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, String> getSelectOptions(SelectTag select) {
		OptionTag[] options = select.getOptionTags();
		Map<String, String> ret = new TreeMap<String, String>();
		for (OptionTag option : options) {
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
	public static NodeList getChildTags(Node node, List<String> tagNames) {
		NodeList children = node.getChildren();
		if (children != null) {
			int size = tagNames.size();
			List<NodeFilter> predicates = new ArrayList<NodeFilter>(size);
			for (String name : tagNames) {
				predicates.add(new TagNameFilter(name));
			}
			NodeFilter filter = new OrFilter(predicates.toArray(new NodeFilter[size]));
			return children.extractAllNodesThatMatch(filter, true);
		}
		return null;
	}

	/**
	 * 
	 * @param nl
	 * @return
	 */
	public static List<String> getNameAttributeValues(NodeList nl) {
		List<String> ret = new ArrayList<String>(nl.size());
		for (int i = 0; i < nl.size(); i++) {
			String name = ((TagNode)nl.elementAt(i)).getAttribute(NAME);
			if (name != null) {
				ret.add(name);
			}
		}
		return ret;
	}

//	/**
//	 * 
//	 * @param names
//	 * @param params
//	 * @return
//	 */
//	public static final List<NameValuePair> toListNameValuePair(List<String> names, Map<String, String> params) {
//		ArrayList<NameValuePair> ret = new ArrayList<NameValuePair>(names.size());
//		for (String name : names) {
//			String value = params.get(name);
//			if (value != null) {
//				ret.add(new BasicNameValuePair(name, params.get(name)));
//			}
//		}
//		return ret;
//	}
	
	public static final List<NameValuePair> toListNameValuePair(Map<String, String> params) {
		Set<String> names = params.keySet();
		ArrayList<NameValuePair> ret = new ArrayList<NameValuePair>(names.size());
		for (String name : names) {
			String value = params.get(name);
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
	public static final void fillTextArea(Map<String, String> params, FormTag form, String name, String value) {
		if (form.getTextAreaTag(name) != null & EmptyChecker.isNotNullOrEmpty(value)) {
			params.put(name, value);
		}
	}

//	/**
//	 * 
//	 * @param entity
//	 * @param form
//	 * @param name
//	 * @param value
//	 * @throws UnsupportedEncodingException
//	 */
//	public static final void fillTextArea(MultipartEntity entity, FormTag form, String name, String value) throws UnsupportedEncodingException {
//		if (form.getTextAreaTag(name) != null) {
//			entity.addPart(name, new StringBody(value));
//		}
//	}

	/**
	 * 
	 * @param params
	 * @param form
	 * @param name
	 * @param value
	 */
	public static final void fillInputText(Map<String, String> params, FormTag form, String name, String value) {
		if (form.getInputTag(name) != null & EmptyChecker.isNotNullOrEmpty(value)) {
			params.put(name, value);
		}		
	}

//	/**
//	 * 
//	 * @param entity
//	 * @param form
//	 * @param name
//	 * @param value
//	 * @throws UnsupportedEncodingException
//	 */
//	public static final void fillInputText(MultipartEntity entity, FormTag form, String name, String value) throws UnsupportedEncodingException {
//		if (form.getInputTag(name) != null) {
//			entity.addPart(name, new StringBody(value));
//		}		
//	}

	/**
	 * 
	 * @param params
	 * @param form
	 * @param name
	 * @param value
	 */
	public static final void fillInputCheckbox(Map<String, String> params, FormTag form, String name, boolean value) {
		if (form.getInputTag(name) != null && value) {
			params.put(name, "1");
		}
	}

//	/**
//	 * 
//	 * @param entity
//	 * @param form
//	 * @param name
//	 * @param value
//	 * @throws UnsupportedEncodingException
//	 */
//	public static final void fillInputCheckbox(MultipartEntity entity, FormTag form, String name, boolean value) throws UnsupportedEncodingException {
//		if (form.getInputTag(name) != null && value) {
//			entity.addPart(name, new StringBody("1"));
//		}
//	}

	/**
	 * 
	 * @param entity
	 * @param params
	 * @throws UnsupportedEncodingException
	 */
	public static final void fillParams(MultipartEntity entity, Map<String, String> params) throws UnsupportedEncodingException {
		for (String name : params.keySet()) {
			//if (name != null) {
				entity.addPart(name, new StringBody(params.get(name)));
			//}
		}
	}
}
