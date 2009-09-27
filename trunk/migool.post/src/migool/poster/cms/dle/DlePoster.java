package migool.poster.cms.dle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.http.impl.client.DefaultHttpClient;
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
import migool.post.internal.Image;
import migool.post.tag.TagUtil;
import migool.poster.PostResponse;
import migool.share.image.IImageShare;
import migool.share.image.ImageShareResponse;
import migool.util.EmptyChecker;
import migool.util.HtmlParserUtil;
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
		String url = httpRoot + IDleConstants.ADD_NEWS_PATH;
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		String html = IOUtil.toString(response.getEntity().getContent());

		FormTag form = (FormTag) (new Parser(html)).parse(new AndFilter(new TagNameFilter("form"), new HasAttributeFilter("name", IDleConstants.ENTRYFORM))).elementAt(0);
		NodeList elems = HtmlParserUtil.getChildTags(form, Arrays.asList(new String[]{"input", "select", "textarea"}));
		System.out.println(elems);
		//List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String, String> params = new HashMap<String, String>();

		// filling for default values:
		HtmlParserUtil.setHiddenInputs(form, params);
		NodeList nl = HtmlParserUtil.getNotHiddenInputs(form).extractAllNodesThatMatch(new NotFilter(new HasAttributeFilter("name", "nview")));
		HtmlParserUtil.setInputs(nl, params);

		// filling post:
		// title
		InputTag input = form.getInputTag(IDleConstants.TITLE);
		if (input != null) {
			//params.add(new BasicNameValuePair(input.getAttribute("name"), post.title));
			params.put(input.getAttribute("name"), post.title);
		}
		// URL
		input = form.getInputTag(IDleConstants.ALT_NAME);
		if (input != null) {
			params.put(input.getAttribute("name"), post.url);
		}
		// TODO category
		SelectTag select = (SelectTag) form.getChildren().extractAllNodesThatMatch(new HasAttributeFilter("name", "catlist[]"), true).elementAt(0);
		if (select != null) {
			Map<String, String> cats = HtmlParserUtil.getSelectOptions(select);
			System.out.println(cats);
			//params.add(new BasicNameValuePair("catlist[]", "1"));
			params.put("catlist[]", "1");
		}
		// tags
		input = form.getInputTag(IDleConstants.TAGS);
		if (input != null) {
			//params.add(new BasicNameValuePair(input.getAttribute("name"), TagUtil.toString(post.tags)));
			params.put(input.getAttribute("name"), TagUtil.toString(post.tags));
		}
		// short story
		nl = form.getFormTextareas();
		TextareaTag textarea = (TextareaTag) nl.extractAllNodesThatMatch(new HasAttributeFilter("name", IDleConstants.SHORT_STORY)).elementAt(0);
		if (textarea != null) {
			//params.add(new BasicNameValuePair(textarea.getAttribute("name"), post.shortStory));
			params.put(textarea.getAttribute("name"), post.shortStory);
		}
		// full story
		textarea = (TextareaTag) nl.extractAllNodesThatMatch(new HasAttributeFilter("name", IDleConstants.FULL_STORY)).elementAt(0);
		if (textarea != null) {
			//params.add(new BasicNameValuePair(textarea.getAttribute("name"), post.fullStory));
			params.put(textarea.getAttribute("name"), post.fullStory);
		}
		// other inputs
		Properties inputs = post.inputs;
		if (inputs != null) {
			String k = null;
			for (Object key : inputs.keySet()) {
				k = key.toString();
				nl = form.getChildren().extractAllNodesThatMatch(new HasAttributeFilter("name", k));
				if (nl.size() > 0) {
					//params.add(new BasicNameValuePair(k, inputs.getProperty(k)));
					params.put(k, inputs.getProperty(k));
				}
			}
		}
		
		HttpPost request = new HttpPost(url);
		List<NameValuePair> p = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			p.add(new BasicNameValuePair(key, params.get(key)));
		}
		System.out.println(p);
		request.setEntity(new UrlEncodedFormEntity(p));
		response = client.execute(request);
		html = IOUtil.toString(response.getEntity().getContent());
		if (StringUtil.contains(html, IDleConstants.ERROR_MESSAGES)) {
			return new PostResponse(PostResponse.NOT_POSTED, null);
		}
		form = (FormTag) (new Parser(html)).parse(new AndFilter(new TagNameFilter("form"), new HasAttributeFilter("name", IDleConstants.ENTRYFORM))).elementAt(0);
		
		return (form == null) ? new PostResponse(PostResponse.OK, null): new PostResponse(PostResponse.NOT_POSTED, null);
	}

	@Override
	public String getHost() {
		return host;
	}
}
