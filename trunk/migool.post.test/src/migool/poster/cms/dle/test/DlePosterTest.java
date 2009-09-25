/**
 * 
 */
package migool.poster.cms.dle.test;

import static org.junit.Assert.*;

import migool.host.auth.LoginResponse;
import migool.poster.cms.dle.DlePoster;

import org.junit.Test;

/**
 * @author Denis Migol
 *
 */
public class DlePosterTest {

	private DlePoster poster = new DlePoster(IConstants.HOST_DLE_80);

	/**
	 * Test method for {@link migool.poster.cms.dle.DlePoster#login(migool.host.auth.LoginPassword)}.
	 */
	@Test
	public void testLogin() throws Exception {
		LoginResponse response = poster.login(IConstants.LOGIN_PASSWORD);
		assertNotNull(response);
		assertEquals(response.getCode(), LoginResponse.OK);
	}

	/**
	 * Test method for {@link migool.poster.cms.dle.DlePoster#post(migool.poster.cms.dle.DlePost)}.
	 */
	@Test
	public void testPost() {
		fail("Not yet implemented");
	}

}
