package migool.poster;

import migool.host.auth.LoginResult;
import migool.host.upload.UploadResult;
import migool.http.HttpClient;
import migool.post.Post;
import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public abstract class PosterBase implements IPoster {
	protected final String host;
	protected HttpClient httpClient = new HttpClient();

	public PosterBase(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public int login() {
		return LoginResult.NOT_LOGGED;
	}

	public int upload(Image img) {
		return UploadResult.NOT_UPLOADED;
	}

	public int post(Post post) {
		return PostResult.NOT_POSTED;
	}
}
