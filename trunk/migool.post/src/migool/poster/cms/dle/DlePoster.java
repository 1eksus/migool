package migool.poster.cms.dle;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
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

	@Override
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		// TODO Auto-generated method stub
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(httpRoot);
		HttpResponse response = client.execute(request);
		String html = IOUtil.toString(response.getEntity().getContent());
		Parser parser = new Parser(html);
		NodeList nl = parser.parse(new TagNameFilter("form"));
		String login = null;
		for (String value : IDleConstants.LOGIN_INPUTS) {
			NodeList nl1 = nl.extractAllNodesThatMatch(new AndFilter(new NodeFilter[] {new TagNameFilter("input"), new HasAttributeFilter("name", value), new HasAttributeFilter("type", "text")}), true);
			if (nl1.size() > 0) {
				login = value;
			}
		}
		String password = null;
		for (String value : IDleConstants.PASS_INPUTS) {
			NodeList nl1 = nl.extractAllNodesThatMatch(new HasAttributeFilter("name", value), true);
			if (nl1.size() > 0) {
				password = value;
			}
		}
		System.out.println(login + ";" + password);
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
