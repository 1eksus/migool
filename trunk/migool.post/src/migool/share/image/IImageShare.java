package migool.share.image;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import migool.host.IHostClient;
import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IImageShare extends IHostClient {
	ImageShareResponse upload(Image img) throws ClientProtocolException, IOException, Exception;
}
