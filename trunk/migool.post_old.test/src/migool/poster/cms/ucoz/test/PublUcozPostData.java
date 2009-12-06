package migool.poster.cms.ucoz.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import migool.post.internal.Image;
import migool.poster.cms.ucoz.post.PublUcozPost;
import migool.util.IOUtil;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public final class PublUcozPostData {
	private static final File file = new File("d:/den/1.jpg");
	private static final PublUcozPost post = new PublUcozPost();
	
	static {
		post.aemail = "email@mail.ru";
		post.aname = "aname";
		post.asite = "asite.ru";
		post.brief = "brief";
		try {
			post.files = new ArrayList<Image>() {
				{
					Image img = new Image();
					img.bytes = IOUtil.toByteArray(new FileInputStream(file));
					img.fileName = file.getName();
					add(img);
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.message = "message";
		post.ocat = "";
		post.source = "source";
		post.title = "title";
	}
	
	private PublUcozPostData() {
	}
	
	public static final PublUcozPost get() {
		return post;
	}
}
