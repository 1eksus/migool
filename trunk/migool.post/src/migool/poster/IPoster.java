package migool.poster;

import migool.post.Post;
import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IPoster {
	/**
	 * 
	 * @return
	 */
	String getHost();

	/**
	 * 
	 * @return
	 */
	int login();

	/**
	 * 
	 * @param img
	 * @return
	 */
	int upload(Image img);

	/**
	 * 
	 * @param post
	 * @return
	 */
	int post(Post post);
}