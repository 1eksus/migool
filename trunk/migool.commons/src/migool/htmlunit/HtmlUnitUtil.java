package migool.htmlunit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class HtmlUnitUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private HtmlUnitUtil() {
	}

	public static BrowserVersion getRandomBrowserVersion() {
		final double random = new Random().nextDouble();
		if (random < 0.1) {
			return BrowserVersion.INTERNET_EXPLORER_6;
		} else if (random < 0.3) {
			return BrowserVersion.INTERNET_EXPLORER_8;
		} else if (random < 0.55) {
			return BrowserVersion.INTERNET_EXPLORER_7;
		} else {
			return BrowserVersion.FIREFOX_3;
		}
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static HtmlSubmitInput getSubmitInput(final HtmlForm form) {
		final List<HtmlElement> inputs = form.getHtmlElementsByTagName(HtmlInput.TAG_NAME);
		for (final HtmlElement element : inputs) {
			final HtmlInput input = (HtmlInput) element;
			if ("submit".equalsIgnoreCase(input.getTypeAttribute())) {
				return (HtmlSubmitInput) input;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param select
	 * @param text
	 * @return
	 */
	public static HtmlOption getOptionByTextValue(final HtmlSelect select, final String text) {
		final List<HtmlOption> options = select.getOptions();
		for (final HtmlOption option : options) {
			if (text.equalsIgnoreCase(option.getText())) {
				return option;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param root
	 * @param src
	 * @return
	 */
	public static HtmlImage getImageBySrc(final HtmlElement root, final String src) {
		final List<HtmlElement> elems = root.getElementsByTagName(HtmlImage.TAG_NAME);
		for (final HtmlElement elem : elems) {
			final HtmlImage img = (HtmlImage) elem;
			if (src.equalsIgnoreCase(img.getSrcAttribute())) {
				return img;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param forms
	 * @param action
	 * @return
	 */
	public static HtmlForm getFormByAction(final List<HtmlForm> forms, final String action) {
		for (final HtmlForm form : forms) {
			if (action.equalsIgnoreCase(form.getActionAttribute())) {
				return form;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param forms
	 * @return
	 */
	public static HtmlForm getFormWithPasswordInput(final List<HtmlForm> forms) {
		for (final HtmlForm form : forms) {
			for (final HtmlElement input : form.getElementsByTagName("input")) {
				if (input.getAttribute("type").equalsIgnoreCase("password")) {
					return form;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static HtmlButtonInput getButtonInput(final HtmlForm form) {
		return (HtmlButtonInput) form.getElementsByAttribute(HtmlInput.TAG_NAME, "type", "button").get(0);
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static HtmlTextInput getTextInput(final HtmlForm form) {
		return (HtmlTextInput) form.getElementsByAttribute(HtmlInput.TAG_NAME, "type", "text").get(0);
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static HtmlPasswordInput getPasswordInput(final HtmlForm form) {
		return (HtmlPasswordInput) form.getElementsByAttribute(HtmlInput.TAG_NAME, "type", "password").get(0);
	}

	/**
	 * 
	 * @param form
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public static HtmlInput getInputByAttribute(final HtmlForm form, final String attributeName,
			final String attributeValue) {
		return (HtmlInput) form.getElementsByAttribute(HtmlInput.TAG_NAME, attributeName, attributeValue).get(0);
	}

	/**
	 * 
	 * @param page
	 * @return
	 */
	public static List<String> getAnchorsHrefAttribute(final HtmlPage page) {
		final List<HtmlAnchor> anchors = page.getAnchors();
		final List<String> ret = new ArrayList<String>(anchors.size());
		for (final HtmlAnchor anchor : anchors) {
			ret.add(anchor.getHrefAttribute());
		}
		return ret;
	}

	/**
	 * 
	 * @param page
	 * @return
	 */
	public static List<String> getUrls(final HtmlPage page) {
		final List<String> hrefs = getAnchorsHrefAttribute(page);
		final List<String> ret = new ArrayList<String>(hrefs);
		return ret;
	}
}
