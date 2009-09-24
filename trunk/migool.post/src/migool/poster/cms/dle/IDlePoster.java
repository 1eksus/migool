package migool.poster.cms.dle;

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
	int post(DlePost post);
}
