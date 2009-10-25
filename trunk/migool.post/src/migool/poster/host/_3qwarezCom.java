package migool.poster.host;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.post.Post;
import migool.poster.IPoster;
import migool.poster.PostResponse;
import migool.poster.PosterInfo;

/**
 * 
 * @author Denis Migol
 *
 */
public class _3qwarezCom implements IPoster {

	public static final String HOST = "3qwarez.com";

	@Override
	public PostResponse post(Post post, PosterInfo info) {
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

	@Override
	public PosterInfo getPosterInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
