package migool.poster.host;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.post.Post;
import migool.poster.IPoster;
import migool.poster.PostInfo;
import migool.poster.PostResponse;

/**
 * 
 * @author Denis Migol
 *
 */
public class _44ukRu implements IPoster {

	public static final String HOST = "44uk.ru";

	@Override
	public String getHost() {
		return HOST;
	}

	@Override
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostInfo getPostInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostResponse post(Post post) {
		// TODO Auto-generated method stub
		return null;
	}
}
