package migool.poster.cms.dle;

import static migool.util.HtmlParserUtil.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import org.apache.http.message.BasicNameValuePair;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.TextareaTag;
import org.htmlparser.util.NodeList;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.http.client.HttpClientFactory;
import migool.post.internal.Image;
import migool.post.util.PostUtil;
import migool.poster.PostResponse;
import migool.poster.PosterInfo;
import migool.share.image.IImageShare;
import migool.share.image.ImageShareResponse;
import migool.util.EmptyChecker;
import migool.util.IOUtil;
import migool.util.LinkUtil;
import migool.util.Regex;
import migool.util.StringUtil;

/**
 * 
 * @author Denis Migol
 *
 */
public final class DlePoster implements IDlePoster, IImageShare {

	private String host;
	private final String site;
	private final HttpClient client;

	public DlePoster(String host) {
		this.host = host;
		this.site = LinkUtil.createHttpRoot(host);
		//this.client = new DefaultHttpClient();
		this.client = HttpClientFactory.newInstance().newHttpClient();
	}

	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		HttpResponse response = client.execute(new HttpGet(site));

		FormTag form = getLoginForm(IOUtil.toString(response.getEntity().getContent()));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		InputTag input = getInputTag(form, IDleConstants.LOGIN_INPUTS);
		String login = lp.getLogin();
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute(NAME), login));
		}
		input = getInputTag(form, IDleConstants.PASS_INPUTS);
		if (input != null) {
			params.add(new BasicNameValuePair(input.getAttribute(NAME), lp.getPassword()));
		}
		setHiddenInputs(form, params);

		HttpPost request = new HttpPost(site);
		request.setEntity(new UrlEncodedFormEntity(params));
		response = client.execute(request);
		response.getEntity().getContent().close();
		response = client.execute(new HttpGet(site));
		String html = IOUtil.toString(response.getEntity().getContent());
		return (html.contains(login)) ? new LoginResponse(LoginResponse.OK) : new LoginResponse(LoginResponse.ERROR);
	}

	public ImageShareResponse upload(Image img) throws ClientProtocolException, IOException, Exception {
		String url = site + IDleConstants.UPLOAD_PATH;
		HttpUriRequest request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		String html = IOUtil.toString(response.getEntity().getContent());

		FormTag form = (FormTag) (new Parser(html)).parse(new AndFilter(new TagNameFilter("form"), new HasChildFilter(new HasAttributeFilter("type", "file"), true))).elementAt(0);
		MultipartEntity entity = new MultipartEntity();
		InputTag file = (InputTag) form.getFormInputs().extractAllNodesThatMatch(new HasAttributeFilter("type", "file"), true).elementAt(0);
		entity.addPart(file.getAttribute(NAME), new InputStreamBody(new ByteArrayInputStream(img.bytes), img.fileName));
		setHiddenInputs(form, entity);

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

	public PostResponse post(IDlePost post) throws ClientProtocolException, IOException, Exception {
		String url = site + IDleConstants.ADD_NEWS_PATH;
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		String html = IOUtil.toString(response.getEntity().getContent());

		FormTag form = (FormTag) (new Parser(html)).parse(new AndFilter(new TagNameFilter("form"), new HasAttributeFilter(NAME, IDleConstants.ENTRYFORM))).elementAt(0);
		Map<String, String> params = new HashMap<String, String>();

		// filling for default values:
		setHiddenInputs(form, params);
		NodeList nl = getNotHiddenInputs(form).extractAllNodesThatMatch(new NotFilter(new HasAttributeFilter(NAME, "nview")));
		setInputs(nl, params);

		// filling post:
		// title
		InputTag input = form.getInputTag(IDleConstants.TITLE);
		if (input != null) {
			params.put(input.getAttribute(NAME), post.getTitle());
		}
		// URL
		input = form.getInputTag(IDleConstants.ALT_NAME);
		if (input != null) {
			params.put(input.getAttribute(NAME), post.getUrl());
		}
		// TODO category
		SelectTag select = (SelectTag) form.getChildren().extractAllNodesThatMatch(new HasAttributeFilter(NAME, "catlist[]"), true).elementAt(0);
		if (select != null) {
			Map<String, String> cats = getSelectOptions(select);
			System.out.println(cats);
			params.put("catlist[]", "1");
		}
		// tags
		input = form.getInputTag(IDleConstants.TAGS);
		if (input != null) {
			params.put(input.getAttribute(NAME), PostUtil.toString(post.getTags()));
		}
		// short story
		nl = form.getFormTextareas();
		TextareaTag textarea = (TextareaTag) nl.extractAllNodesThatMatch(new HasAttributeFilter(NAME, IDleConstants.SHORT_STORY)).elementAt(0);
		if (textarea != null) {
			params.put(textarea.getAttribute(NAME), post.getShortStory());
		}
		// full story
		textarea = (TextareaTag) nl.extractAllNodesThatMatch(new HasAttributeFilter(NAME, IDleConstants.FULL_STORY)).elementAt(0);
		if (textarea != null) {
			params.put(textarea.getAttribute(NAME), post.getFullStory());
		}
		// other inputs
		Properties inputs = post.getInputs();
		if (inputs != null) {
			String k = null;
			for (Object key : inputs.keySet()) {
				k = key.toString();
				nl = form.getChildren().extractAllNodesThatMatch(new HasAttributeFilter(NAME, k));
				if (nl.size() > 0) {
					params.put(k, inputs.getProperty(k));
				}
			}
		}

		// do request
		HttpPost request = new HttpPost(url);
		// TODO charset detection
		request.setEntity(new UrlEncodedFormEntity(toListNameValuePair(params), "windows-1251"));
		request.setHeader("Referer", url);
		response = client.execute(request);

		html = IOUtil.toString(response.getEntity().getContent());
		System.out.println(html);
		if (StringUtil.contains(html, IDleConstants.ERROR_MESSAGES)) {
			return new PostResponse(PostResponse.NOT_POSTED, null);
		}
		form = (FormTag) (new Parser(html)).parse(new AndFilter(new TagNameFilter("form"), new HasAttributeFilter(NAME, IDleConstants.ENTRYFORM))).elementAt(0);
		
		return (form == null) ? new PostResponse(PostResponse.OK, null): new PostResponse(PostResponse.NOT_POSTED, null);
	}

	public String getHost() {
		return host;
	}

	@Override
	public PosterInfo getPosterInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
