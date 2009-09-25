package migool.poster.cms.dle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.poster.PostResponse;
import migool.util.IOUtil;
import migool.util.LinkUtil;

/**
 * 
 * @author Denis Migol
 *
 */
public final class DlePoster implements IDlePoster {

	private String host;
	private String httpRoot;

	public DlePoster(String host) {
		this.host = host;
		this.httpRoot = LinkUtil.createHttpRoot(host);
	}

	/**
	 * Searches form which contains password input and returns it.
	 * @param html
	 * @return
	 */
	private static FormTag getLoginForm(String html) {
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
	private static InputTag getInputTag(FormTag form, String[] names) {
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
	private static NodeList getHiddenInputs(FormTag form) {
		return form.getFormInputs().extractAllNodesThatMatch(new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("type", "hidden")), true);
	}

	@Override
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(new HttpGet(httpRoot));

		FormTag form = getLoginForm(IOUtil.toString(response.getEntity().getContent()));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login_name", "dle"));
		params.add(new BasicNameValuePair("login_password", "dle"));

		InputTag input = getInputTag(form, IDleConstants.LOGIN_INPUTS);
		String login = lp.getLogin();
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute("name"), login));
		}
		input = getInputTag(form, IDleConstants.PASS_INPUTS);
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute("name"), lp.getPassword()));
		}
		NodeList hiddens = getHiddenInputs(form);
		for (int i = 0; i < hiddens.size(); i++) {
			input = (InputTag) hiddens.elementAt(i);
			params.add(new BasicNameValuePair(input.getAttribute("name"), input.getAttribute("value")));
		}

		HttpPost request = new HttpPost(httpRoot);
		request.setEntity(new UrlEncodedFormEntity(params));
		response = client.execute(request);
		String html = IOUtil.toString(response.getEntity().getContent());
		return (html.contains(login)) ? new LoginResponse(LoginResponse.OK) : new LoginResponse(LoginResponse.ERROR);
	}

	@Override
	public PostResponse post(DlePost post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHost() {
		return host;
	}
}
