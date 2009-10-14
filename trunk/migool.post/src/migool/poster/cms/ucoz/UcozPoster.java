package migool.poster.cms.ucoz;

import static migool.poster.cms.ucoz.IUcozConstants.*;
import static migool.poster.cms.ucoz.UcozUtil.*;
import static migool.util.HtmlParserUtil.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.message.BasicNameValuePair;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.FormTag;
import org.htmlparser.util.NodeList;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.http.client.HttpClientFactory;
import migool.post.internal.Image;
import migool.poster.PostResponse;
import migool.poster.cms.ICMSPoster;
import migool.poster.cms.ucoz.UcozUtil.CategoryEntity;
import migool.poster.cms.ucoz.post.BlogUcozPost;
import migool.poster.cms.ucoz.post.BoardUcozPost;
import migool.poster.cms.ucoz.post.DirUcozPost;
import migool.poster.cms.ucoz.post.FaqUcozPost;
import migool.poster.cms.ucoz.post.GbUcozPost;
import migool.poster.cms.ucoz.post.LoadUcozPost;
import migool.poster.cms.ucoz.post.NewsUcozPost;
import migool.poster.cms.ucoz.post.PhotoUcozPost;
import migool.poster.cms.ucoz.post.PublUcozPost;
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
public class UcozPoster implements ICMSPoster {

	private final String host;
	private final String site;
	private final HttpClient client;

	public UcozPoster(String host) {
		this.host = host;
		this.site = LinkUtil.createHttpRoot(host);
		// this.client = new DefaultHttpClient();
		this.client = HttpClientFactory.newInstance().newHttpClient();
	}

	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		HttpGet get = new HttpGet(site);
		HttpResponse response = client.execute(get);
		String html = IOUtil.toString(response.getEntity().getContent());

		// fill the form
		FormTag form = HtmlParserUtil.getLoginForm(html);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		HtmlParserUtil.setHiddenInputs(form, params);
		params.add(new BasicNameValuePair("user", lp.getLogin()));
		params.add(new BasicNameValuePair("password", lp.getPassword()));

		HttpPost request = new HttpPost(site + LOGIN_POST_PATH);
		request.setEntity(new UrlEncodedFormEntity(params));
		html = IOUtil.toString(client.execute(request).getEntity().getContent());

		html = IOUtil.toString(client.execute(get).getEntity().getContent());

		form = HtmlParserUtil.getLoginForm(html);
		return (form == null) ? new LoginResponse(LoginResponse.OK) : new LoginResponse(LoginResponse.NOT_LOGGED);
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(BlogUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(BoardUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(DirUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(FaqUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(GbUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(LoadUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(NewsUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public final PostResponse post(PhotoUcozPost post) {
		// TODO
		return null;
	}

	/**
	 * 
	 * @param post
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public final PostResponse post(PublUcozPost post) throws ClientProtocolException, IOException, Exception {
		String url = site + PUBL_ADD_PATH;
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		String html = IOUtil.toString(response.getEntity().getContent());
		FormTag form = (FormTag)(new Parser(html)).extractAllNodesThatMatch(new AndFilter(new TagNameFilter("form"), new HasAttributeFilter("name", ADDFORM))).elementAt(0);
		if (form == null) {
			return new PostResponse(PostResponse.ERROR, null);
		}
		List<String> names = getNameAttributeValues(getChildTags(form, Arrays.asList(new String[]{"input", "select", "textarea"})));
		System.out.println(names);
		MultipartEntity entity = new MultipartEntity();
		Map<String, String> params = new HashMap<String, String>();

		// filling for default values:
		setHiddenInputs(form, params);
		NodeList nl = getNotHiddenInputs(form).extractAllNodesThatMatch(new NotFilter(new HasAttributeFilter("name", "nview")));
		setInputs(nl, params);

		// filling post
		// TODO category
		List<CategoryEntity> cats = getCategories(html);
		params.put(OCAT, cats.get(0).value);

		// title
		fillInputText(params, form, TITLE, post.title);

		// brief
		fillTextArea(params, form, BRIEF, post.brief);

		// format_brief
		fillInputCheckbox(params, form, FORMAT_BRIEF, post.format_brief);

		// html_brief
		fillInputCheckbox(params, form, HTML_BRIEF, post.html_brief);

		// message
		fillTextArea(params, form, MESSAGE, post.message);

		// format_message
		fillInputCheckbox(params, form, FORMAT_MESSAGE, post.format_message);

		// html_message
		fillInputCheckbox(params, form, HTML_MESSAGE, post.html_message);

		// files
		List<Image> files = post.files;
		if (files != null && files.size() > 0) {
			int maxFiles = getMaxFilesPublPost(html);
			if (maxFiles > MAX_FILES) {
				maxFiles = MAX_FILES;
			}
			int postFiles = files.size();
			int size = (maxFiles > postFiles) ? maxFiles : postFiles;
			Image img = null;
			for (int i = 0; i < size; i++) {
				img = files.get(i);
				if (img != null) {
					entity.addPart(FILES[i], new InputStreamBody(new ByteArrayInputStream(img.bytes), img.fileName));
				}
			}
		}

		// aname
		fillInputText(params, form, ANAME, post.aname);

		// aemail
		fillInputText(params, form, AEMAIL, post.aemail);

		// asite
		fillInputText(params, form, ASITE, post.asite);

		// source
		fillInputText(params, form, SOURCE, post.source);

		// is_pending
		fillInputCheckbox(params, form, IS_PENDING, post.is_pending);

		// coms_allowed
		fillInputCheckbox(params, form, COMS_ALLOWED, post.coms_allowed);

		// do request
		//HttpPost request = new HttpPost(url);
		HttpPost request = new HttpPost(form.extractFormLocn());
		// TODO charset detection ?
		//request.setEntity(new UrlEncodedFormEntity(toListNameValuePair(names, params), "UTF-8"));
		fillParams(entity, params);
		request.setEntity(entity);
		request.setHeader("Referer", url);
		response = client.execute(request);
		html = IOUtil.toString(response.getEntity().getContent());
		if (EmptyChecker.isNotNullOrEmpty(html) && html.contains("<div class=\"myWinSuccess\">")) {
			String postUrl = (new Regex(html, "http://.*/publ/[0-9\\-]*")).getMatches()[0][0];
			return new PostResponse(PostResponse.OK, postUrl);
		} else {
			return new PostResponse(PostResponse.OK, null);
		}
	}

	public String getHost() {
		return host;
	}
}
