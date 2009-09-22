package migool.host;

import migool.host.upload.UploadResponse;
import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IHostClient {
	public UploadResponse uploadImage(Image img);
	public int login();
}