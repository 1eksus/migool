package migool.host;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResult;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IHostClient {
	//public UploadResponse uploadImage(Image img);
	public LoginResult login(LoginPassword lp);
}