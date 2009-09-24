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

	@Override
	public LoginResponse login(LoginPassword lp) {
		return new LoginResponse(LoginResponse.OK);
	}

	@Override
	public ImageShareResponse upload(Image img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHost() {
		return HOST;
	}
}
