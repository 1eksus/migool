/**
 * 
 */
package migool.poster.cms.dle.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import migool.host.auth.LoginResponse;
import migool.post.internal.Image;
import migool.poster.cms.dle.DlePoster;
import migool.share.image.ImageShareResponse;
import migool.util.IOUtil;

import org.junit.Test;

/**
 * @author Denis Migol
 *
 */
public class DlePosterTest {

	private static final DlePoster poster = new DlePoster(IConstants.HOST_DLE_80);

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
	 * Test method for {@link migool.poster.cms.dle.DlePoster#upload(migool.post.internal.Image)}.
	 */
	@Test
	public void testUpload() throws Exception {
		try {
			String fileName = "d:/den/1.jpg";
			Image img = new Image();
			img.bytes = IOUtil.toByteArray(new FileInputStream(new File(fileName)));

			ImageShareResponse response = poster.upload(img);
			assertNotNull(response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Test method for {@link migool.poster.cms.dle.DlePoster#post(migool.poster.cms.dle.DlePost)}.
	 */
	@Test
	public void testPost() {
		fail("Not yet implemented");
	}

}
