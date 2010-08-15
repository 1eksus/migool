package migool.auth;

import java.io.IOException;

import migool.entity.CaptchaEntity;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ILoginable {
	/**
	 * 
	 * @return
	 */
	CaptchaEntity getLoginCaptcha() throws IOException;

	/**
	 * 
	 * @throws LoginException
	 * @throws IOException
	 * @throws Exception
	 */
	void login(Login login) throws LoginException, IOException, Exception;
}
