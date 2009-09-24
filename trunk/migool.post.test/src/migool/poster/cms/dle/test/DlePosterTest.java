package migool.poster.cms.dle.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import migool.host.auth.LoginResponse;
import migool.host.upload.UploadResponse;
import migool.host.upload.UploadResult;
import migool.http.HttpClient;
import migool.post.internal.Image;
import migool.poster.cms.dle.DlePoster_Old;
import migool.util.IOUtil;

import org.junit.Test;

/**
 * 
 * @author Denis Migol
 *
 */
public class DlePosterTest {
	private static DlePoster_Old poster;
	
	@Test
	public void testLogin() {
		poster = new DlePoster_Old(IConstants.HLP_DLE_80, new HttpClient());
		assertEquals(poster.login(), LoginResponse.OK);
	}

	@Test
	public void testUploadImage() {
		String fileName = "d:\\DEN\\21.jpg";
		Image image = new Image();
		image.setType("image/jpeg");
		try {
			image.setBytes(IOUtil.toByteArray(new FileInputStream(new File(fileName))));
		} catch (FileNotFoundException e) {
			assertFalse(true);
		}
		
		UploadResponse response = poster.uploadImage(image);
		assertEquals(response.result, UploadResult.OK);
	}

//	@Test
//	public void testPost() {
//		fail("Not yet implemented");
//	}
}