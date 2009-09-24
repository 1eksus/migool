package migool.poster;

import migool.host.IHostClient;
import migool.post.Post;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IPoster extends IHostClient {
	/**
	 * 
	 * @param post
	 * @return
	 */
	PostResponse post(Post post);
}