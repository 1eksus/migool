package migool.poster.cms.ucoz;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.tags.FormTag;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.poster.PostResponse;
import migool.poster.cms.ICMSPoster;
import migool.poster.cms.ucoz.post.BlogUcozPost;
import migool.poster.cms.ucoz.post.BoardUcozPost;
import migool.poster.cms.ucoz.post.DirUcozPost;
import migool.poster.cms.ucoz.post.FaqUcozPost;
import migool.poster.cms.ucoz.post.GbUcozPost;
import migool.poster.cms.ucoz.post.LoadUcozPost;
import migool.poster.cms.ucoz.post.NewsUcozPost;
import migool.poster.cms.ucoz.post.PhotoUcozPost;
import migool.poster.cms.ucoz.post.PublUcozPost;
import migool.util.HtmlParserUtil;
import migool.util.IOUtil;
import migool.util.LinkUtil;

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
		this.client = new DefaultHttpClient();
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
	 */
	public final PostResponse post(PublUcozPost post) {
		// TODO
		return null;
	}

	public String getHost() {
		return host;
	}

	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		HttpGet get = new HttpGet(site);
		HttpResponse response = client.execute(get);
		String html = IOUtil.toString(response.getEntity().getContent());
		FormTag form = HtmlParserUtil.getLoginForm(html);
		System.out.println(form);
		// TODO Auto-generated method stub
		return null;
	}
}
