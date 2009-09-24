package migool.poster.cms.dle;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(httpRoot);
		HttpResponse response = client.execute(request);
		String html = IOUtil.toString(response.getEntity().getContent());
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
