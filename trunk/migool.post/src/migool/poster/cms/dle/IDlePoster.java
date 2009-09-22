package migool.poster.cms.dle;

import migool.host.IHostClient;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IDlePoster extends IHostClient {
	/**
	 * 
	 * @param post
	 * @return
	 */
	int post(DlePost post);
}
