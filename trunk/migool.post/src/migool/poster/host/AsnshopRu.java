package migool.poster.host;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.post.Post;
import migool.poster.IPoster;
import migool.poster.PostResponse;

/**
 * 
 * @author Denis Migol
 *
 */
public class AsnshopRu implements IPoster {

	public static final String HOST = "asnshop.ru";

	@Override
	public PostResponse post(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHost() {
		return HOST;
	}

	@Override
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
