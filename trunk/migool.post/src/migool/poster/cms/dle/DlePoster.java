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
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.post.internal.Image;
import migool.poster.PostResponse;
import migool.share.image.IImageShare;
import migool.share.image.ImageShareResponse;
import migool.util.HtmlParserUtil;
import migool.util.IOUtil;
import migool.util.LinkUtil;

/**
 * 
 * @author Denis Migol
 *
 */
public final class DlePoster implements IDlePoster, IImageShare {

	private String host;
	private String httpRoot;

	public DlePoster(String host) {
		this.host = host;
		this.httpRoot = LinkUtil.createHttpRoot(host);
	}

	@Override
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(new HttpGet(httpRoot));

		FormTag form = HtmlParserUtil.getLoginForm(IOUtil.toString(response.getEntity().getContent()));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login_name", "dle"));
		params.add(new BasicNameValuePair("login_password", "dle"));

		InputTag input = HtmlParserUtil.getInputTag(form, IDleConstants.LOGIN_INPUTS);
		String login = lp.getLogin();
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute("name"), login));
		}
		input = HtmlParserUtil.getInputTag(form, IDleConstants.PASS_INPUTS);
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute("name"), lp.getPassword()));
		}
		NodeList hiddens = HtmlParserUtil.getHiddenInputs(form);
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
	public ImageShareResponse upload(Image img) {
		// TODO Auto-generated method stub
		return null;
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
