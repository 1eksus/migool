package migool.poster;

import migool.entity.CaptchaEntity;

/**
 * 
 * @author Denis Migol
 *
 */
public class PostInfo {
	public final CaptchaEntity captcha;
	public final String paramName;

	public PostInfo(CaptchaEntity captcha, String paramName) {
		this.captcha = captcha;
		this.paramName = paramName;
	}
}
