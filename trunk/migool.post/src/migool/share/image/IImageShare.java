package migool.share.image;

import migool.host.IHostClient;
import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IImageShare extends IHostClient {
	ImageShareResponse upload(Image img);
}
