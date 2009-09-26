package migool.poster.cms.dle;

import java.io.ByteArrayInputStream;
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
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
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

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.post.internal.Image;
import migool.poster.PostResponse;
import migool.share.image.IImageShare;
import migool.share.image.ImageShareResponse;
import migool.util.EmptyChecker;
import migool.util.HtmlParserUtil;
import migool.util.IOUtil;
import migool.util.LinkUtil;
import migool.util.Regex;

/**
 * 
 * @author Denis Migol
 *
 */
public final class DlePoster implements IDlePoster, IImageShare {

	private String host;
	private String httpRoot;
	private final HttpClient client;

	public DlePoster(String host) {
		this.host = host;
		this.httpRoot = LinkUtil.createHttpRoot(host);
		this.client = new DefaultHttpClient();
	}

	@Override
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		HttpResponse response = client.execute(new HttpGet(httpRoot));

		FormTag form = HtmlParserUtil.getLoginForm(IOUtil.toString(response.getEntity().getContent()));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("login_name", "dle"));
//		params.add(new BasicNameValuePair("login_password", "dle"));

		InputTag input = HtmlParserUtil.getInputTag(form, IDleConstants.LOGIN_INPUTS);
		String login = lp.getLogin();
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute("name"), login));
		}
		input = HtmlParserUtil.getInputTag(form, IDleConstants.PASS_INPUTS);
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute("name"), lp.getPassword()));
		}
		HtmlParserUtil.setHiddenInputs(form, params);

		HttpPost request = new HttpPost(httpRoot);
		request.setEntity(new UrlEncodedFormEntity(params));
		response = client.execute(request);
		response.getEntity().getContent().close();
		response = client.execute(new HttpGet(httpRoot));
		String html = IOUtil.toString(response.getEntity().getContent());
		return (html.contains(login)) ? new LoginResponse(LoginResponse.OK) : new LoginResponse(LoginResponse.ERROR);
	}

	@Override
	public ImageShareResponse upload(Image img) throws ClientProtocolException, IOException, Exception {
		String url = httpRoot + IDleConstants.UPLOAD_PATH;
		HttpUriRequest request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		String html = IOUtil.toString(response.getEntity().getContent());

		FormTag form = (FormTag) (new Parser(html)).parse(new AndFilter(new TagNameFilter("form"), new HasChildFilter(new HasAttributeFilter("type", "file"), true))).elementAt(0);
		MultipartEntity entity = new MultipartEntity();
		InputTag file = (InputTag) form.getFormInputs().extractAllNodesThatMatch(new HasAttributeFilter("type", "file"), true).elementAt(0);
		entity.addPart(file.getAttribute("name"), new InputStreamBody(new ByteArrayInputStream(img.bytes), img.fileName));
		HtmlParserUtil.setHiddenInputs(form, entity);

		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		response = client.execute(post);
		html = IOUtil.toString(response.getEntity().getContent());
		String link = new Regex(html, "http://[\\/\\w\\-_]+" + img.fileName).getMatches()[0][0];
		ImageShareResponse ret = new ImageShareResponse();
		if (EmptyChecker.isNotNullOrEmpty(link)) {
			ret.setCode(ImageShareResponse.OK);
			ret.setLink(link);
		} else {
			ret.setCode(ImageShareResponse.ERROR);
		}
		return ret;
	}

	@Override
	public PostResponse post(DlePost post) throws ClientProtocolException, IOException, Exception {
		// TODO Auto-generated method stub
		String url = httpRoot + IDleConstants.ADD_NEWS_PATH;
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		String html = IOUtil.toString(response.getEntity().getContent());

		FormTag form = (FormTag) (new Parser(html)).parse(new AndFilter(new TagNameFilter("form"), new HasAttributeFilter("name", "entryform"))).elementAt(0);
		System.out.println(form);
		NodeList nl = form.getFormInputs();
		System.out.println(nl);
		nl = form.getFormTextareas();
		System.out.println(nl);
		nl = form.getChildren();
		System.out.println(nl);
		return null;
	}

	@Override
	public String getHost() {
		return host;
	}
}
