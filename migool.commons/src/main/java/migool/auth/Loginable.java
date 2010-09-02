package migool.auth;

import java.io.IOException;

import migool.entity.CaptchaEntity;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface Loginable {
	/**
	 * 
	 * @return
	 */
	CaptchaEntity getLoginCaptcha() throws IOException;

	/**
	 * 
	 * @throws LoginException
	 * @throws IOException
	 */
	void login(Login login) throws LoginException, IOException;
}
