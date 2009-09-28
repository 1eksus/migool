package migool.share.image.host;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.post.internal.Image;
import migool.share.image.IImageShare;
import migool.share.image.ImageShareResponse;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RadikalRu implements IImageShare {
	
	public static final String HOST = "radikal.ru";

	public LoginResponse login(LoginPassword lp) {
		return new LoginResponse(LoginResponse.OK);
	}

	public ImageShareResponse upload(Image img) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHost() {
		return HOST;
	}
}
