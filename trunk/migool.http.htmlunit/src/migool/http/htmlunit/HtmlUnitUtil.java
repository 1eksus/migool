package migool.http.htmlunit;

import java.util.Iterator;
import java.util.List;

import migool.GlobalOptions;

import org.w3c.dom.NamedNodeMap;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

/**
 * 
 * @author Denis Migol
 *
 */
public abstract class HtmlUnitUtil {

	/**
	 * 
	 * @return
	 */
	public static WebClient newWebClient() {
		WebClient webClient = null;
		if (GlobalOptions.isProxySetted()) {
			webClient = new WebClient(BrowserVersion.FIREFOX_3, GlobalOptions.getProxyHost(), GlobalOptions
					.getProxyPort());
		} else {
			webClient = new WebClient();
		}
		webClient.setThrowExceptionOnFailingStatusCode(false);
		webClient.setThrowExceptionOnScriptError(false);
		return webClient;
	}

	/**
	 * 
	 * @param forms
	 * @param action
	 * @return
	 */
	public static HtmlForm getFormByAction(List<HtmlForm> forms, String action) {
		HtmlForm form = null;
		for (Iterator<HtmlForm> iterator = forms.iterator(); iterator.hasNext();) {
			form = (HtmlForm) iterator.next();
			if (form.getActionAttribute().equalsIgnoreCase(action))
				return form;
			// System.out.println(temp.asXml());
		}
		return null;
	}

	/**
	 * 
	 * @param forms
	 * @param id
	 * @return
	 */
	public static HtmlForm getFormById(List<HtmlForm> forms, String id) {
		HtmlForm form = null;
		for (Iterator<HtmlForm> iterator = forms.iterator(); iterator.hasNext();) {
			form = (HtmlForm) iterator.next();
			if (form.getId().equalsIgnoreCase(id))
				return form;
			// System.out.println(temp.asXml());
		}
		return null;
	}

	/**
	 * 
	 * @param forms
	 * @param prefixId
	 * @return
	 */
	public static HtmlForm getFromByPrefixId(List<HtmlForm> forms, String prefixId) {
		HtmlForm form = null;
		for (Iterator<HtmlForm> iterator = forms.iterator(); iterator.hasNext();) {
			form = (HtmlForm) iterator.next();
			if (form.getId().startsWith(prefixId))
				return form;
		}
		return null;
	}

	/**
	 * 
	 * @param forms
	 * @return
	 */
	public static String printHtmlForms(List<HtmlForm> forms) {
		String res = "";
		for (Iterator<HtmlForm> iterator = forms.iterator(); iterator.hasNext();) {
			HtmlForm htmlForm = (HtmlForm) iterator.next();
			res += htmlForm.asXml();
		}
		return res;
	}

	/**
	 * 
	 * @param form
	 * @return
	 */
	public static HtmlInput getSubmitInput(HtmlForm form) {
		for (HtmlElement element : form.getAllHtmlChildElements()) {
			if (element.getAttribute("type").equalsIgnoreCase("submit")) {
				return (HtmlInput) element;
			}
			if (element.getAttribute("value").equalsIgnoreCase("submit")) {
				return (HtmlInput) element;
			}
			if (element.getAttribute("onclick").equalsIgnoreCase("submit();")) {
				return (HtmlInput) element;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param form
	 * @param name
	 * @return
	 */
	public static boolean isFormContainsInputName(HtmlForm form, String name) {
		HtmlInput input = null;
		try {
			input = form.getInputByName(name);
		} catch (ElementNotFoundException e) {
		}
		return (input != null);
	}

	/**
	 * 
	 * @param forms
	 * @param name
	 * @return
	 */
	public static HtmlForm getFormByInputName(List<HtmlForm> forms, String name) {
		for (Iterator<HtmlForm> iterator = forms.iterator(); iterator.hasNext();) {
			HtmlForm form = (HtmlForm) iterator.next();
			if (isFormContainsInputName(form, name)) {
				return form;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	public static HtmlElement getElementByName(HtmlPage page, String name) {
		try {
			List<HtmlElement> elements = page.getHtmlElementsByName(name);
			if (elements != null && elements.size() > 0) {
				int i = 0;
				HtmlElement element = elements.get(i);
				while (element == null) {
					i++;
					element = elements.get(i);
				}
				return element;
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	public static HtmlElement getSubmitElementByName(HtmlPage page, String name) {
		List<HtmlElement> elements = page.getHtmlElementsByName(name);
		for (Iterator<HtmlElement> iterator = elements.iterator(); iterator.hasNext();) {
			HtmlElement element = (HtmlElement) iterator.next();
			// if (element.getAttribute("type").equalsIgnoreCase("submit")
			// || element.getAttribute("type").equalsIgnoreCase("image"))
			// return element;
			NamedNodeMap attrs = element.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				String attrName = attrs.item(i).getNodeName();
				String attrValue = attrs.item(i).getNodeValue();
				if (attrName.equalsIgnoreCase("type") && attrValue.equalsIgnoreCase("submit"))
					return element;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param select
	 * @param numOption
	 */
	public static void chooseSelect(final HtmlSelect select, final int numOption) {
		List<HtmlOption> options = select.getOptions();
		int i = 0;
		for (Iterator<HtmlOption> iterator = options.iterator(); iterator.hasNext();) {
			HtmlOption option = (HtmlOption) iterator.next();
			if (i == numOption) {
				option.setSelected(true);
				return;
			}
			i++;
		}
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String xml2html(String data) {
		String ret = data;
		return ret;
	}
}
