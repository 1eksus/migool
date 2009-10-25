package migool.poster.host;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.post.Post;
import migool.poster.IPoster;
import migool.poster.PostResponse;
import migool.poster.PosterInfo;
import migool.poster.cms.dle.DlePoster;

/**
 * 
 * @author Denis Migol
 * 
 */
public class _4erda4okCom implements IPoster {

	public static final String HOST = "4erda4ok.com";

	private final DlePoster poster = new DlePoster(HOST);

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
		return poster.login(lp);
	}

	@Override
	public PosterInfo getPosterInfo() {
		return poster.getPosterInfo();
	}
}
