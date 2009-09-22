package migool.poster.cms.dle.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import migool.host.auth.LoginResult;
import migool.host.upload.UploadResponse;
import migool.host.upload.UploadResult;
import migool.http.HttpClient;
import migool.post.internal.Image;
import migool.poster.cms.dle.DlePoster;

import org.junit.Test;

/**
 * 
 * @author Denis Migol
 *
 */
public class DlePosterTest {
	private static DlePoster poster;
	
	private static final byte[] read(InputStream in) {
		try {
			int length = 0;
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			while ((length = in.available()) > 0) {
				byte[] buf = new byte[length];
				int read = 0;
				while ((read = in.read(buf)) > 0) {
					bo.write(buf, 0, read);
				}
			}
			return bo.toByteArray();
		} catch (Exception e) {
		}
		return null;
	}

	@Test
	public void testLogin() {
		poster = new DlePoster(IConstants.HLP_DLE_80, new HttpClient());
		assertEquals(poster.login(), LoginResult.OK);
	}

	@Test
	public void testUploadImage() {
		String fileName = "d:\\DEN\\21.jpg";
		Image image = new Image();
		image.type = "image/jpeg";
		try {
			image.bytes = read(new FileInputStream(new File(fileName)));
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