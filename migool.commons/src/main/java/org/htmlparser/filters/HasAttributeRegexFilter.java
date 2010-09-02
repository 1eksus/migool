package org.htmlparser.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Tag;

/**
 * 
 * @author Denis Migol
 * 
 */
public class HasAttributeRegexFilter implements NodeFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5841401639986618781L;

	/**
	 * The attribute to check for.
	 */
	protected String mAttribute;

	/**
	 * The regex value to check for.
	 */
	protected String mPatternString;

	protected Pattern mPattern;

	/**
	 * 
	 */
	public HasAttributeRegexFilter() {
		this("", null);
	}

	/**
	 * 
	 * @param attribute
	 */
	public HasAttributeRegexFilter(final String attribute) {
		this(attribute, null);
	}

	/**
	 * 
	 * @param attribute
	 * @param regexValue
	 */
	public HasAttributeRegexFilter(final String attribute, final String pattern) {
		mAttribute = attribute;
		setPattern(pattern);
	}

	/**
	 * 
	 * @param name
	 */
	public void setAttributeName(final String name) {
		mAttribute = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getAttributeName() {
		return mAttribute;
	}

	/**
	 * 
	 * @param pattern
	 */
	public void setPattern(final String pattern) {
		mPatternString = pattern;
		mPattern = Pattern.compile(pattern);
	}

	/**
	 * 
	 * @return
	 */
	public String getPattern() {
		return mPatternString;
	}

	@Override
	public boolean accept(Node node) {
		boolean ret = false;
		if (node instanceof Tag) {
			Attribute attribute = ((Tag) node).getAttributeEx(mAttribute);
			ret = null != attribute;
			if (ret && (null != mPatternString)) {
				Matcher matcher = mPattern.matcher(attribute.getValue());
				ret = matcher.matches();
			}
		}
		return ret;
	}

}
