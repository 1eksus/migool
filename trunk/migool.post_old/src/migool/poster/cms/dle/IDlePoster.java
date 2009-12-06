package migool.poster.cms.dle;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import migool.poster.PostInfo;
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
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	PostResponse post(IDlePost post) throws ClientProtocolException, IOException, Exception;

	/**
	 * 
	 * @return
	 */
	PostInfo getPostInfo();
}
