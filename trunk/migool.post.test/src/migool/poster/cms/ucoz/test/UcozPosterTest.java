package migool.poster.cms.ucoz.test;

import static org.junit.Assert.*;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;
import migool.poster.cms.ucoz.UcozPoster;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UcozPosterTest {
	
	private static final UcozPoster poster = new UcozPoster("ucoztest.at.ua");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() throws Exception {
		LoginResponse response = poster.login(new LoginPassword("migooltest@gmail.com", "Migool_Test"));
		assertNotNull(response);
		assertEquals(response.getCode(), LoginResponse.OK);

		UcozPoster newPoster = new UcozPoster(poster.getHost());
		response = newPoster.login(new LoginPassword("migooltest@gmail.com", "Migool_Tst"));
		assertNotNull(response);
		assertTrue(response.getCode() != LoginResponse.OK);
		//assertEquals(response.getCode(), LoginResponse.NOT_LOGGED);
	}

//	@Test
//	public void testUcozPoster() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostBlogUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostBoardUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostDirUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostFaqUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostGbUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostLoadUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostNewsUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostPhotoUcozPost() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPostPublUcozPost() {
//		fail("Not yet implemented");
//	}
}
