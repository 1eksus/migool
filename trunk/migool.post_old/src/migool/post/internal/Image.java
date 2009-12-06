package migool.post.internal;

import java.util.List;

import migool.share.image.IImageShare;

/**
 * 
 * @author Denis Migol
 *
 */
public class Image {
	public byte[] bytes;
	public String type;
	public String fileName;
	/** original link of image **/
	public String link;
	/** links of this image on Image Shares **/
	public List<IImageShare> links;
}
