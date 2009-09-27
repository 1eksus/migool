package migool.poster.cms.dle.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import migool.host.auth.LoginResponse;
import migool.post.internal.Image;
import migool.poster.PostResponse;
import migool.poster.cms.dle.DlePost;
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
		String fileName = "d:/den/1.jpg";
		File file = new File(fileName);
		Image img = new Image();
		img.fileName = file.getName();
		img.bytes = IOUtil.toByteArray(new FileInputStream(file));

		ImageShareResponse response = poster.upload(img);
		assertNotNull(response);
		assertEquals(response.getCode(), ImageShareResponse.OK);
	}

	/**
	 * Test method for {@link migool.poster.cms.dle.DlePoster#post(migool.poster.cms.dle.DlePost)}.
	 */
	@Test
	public void testPost() throws Exception {
		DlePost post = new DlePost();
		post.title = "заголовок";
		post.url = "урла";
		post.categories = null;
		post.shortStory = "краткая новость";
		post.fullStory = "полная новость";
		post.tags = Arrays.asList(new String[]{"тэг_1", "тэг_2", "тэг 3"});
		PostResponse response = poster.post(post);
		assertNotNull(response);
		assertEquals(response.getCode(), PostResponse.OK);
	}
}
