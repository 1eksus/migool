package migool.poster.cms.dle;

import migool.poster.PostResponse;
import migool.poster.cms.ICMSPoster;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IDlePoster extends ICMSPoster {
	/**
	 * 
	 * @param post
	 * @return
	 */
	PostResponse post(DlePost post);
}
