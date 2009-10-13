package migool.poster.cms.ucoz.test;

import java.util.ArrayList;

import migool.post.internal.Image;
import migool.poster.cms.ucoz.post.PublUcozPost;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public final class PublUcozPostData {
	private static final PublUcozPost post = new PublUcozPost();
	
	static {
		post.aemail = "email@mail.ru";
		post.aname = "aname";
		post.asite = "asite.ru";
		post.brief = "brief";
		post.files = new ArrayList<Image>() {
			{
				add(new Image());
			}
		};
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
