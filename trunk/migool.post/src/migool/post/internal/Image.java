package migool.post.internal;

import java.util.List;

/**
 * 
 * @author Denis Migol
 *
 */
public class Image {
	private byte[] bytes;
	private String type;
	/** links of this image on Image Shares **/
	private List<String> links;

	public Image(byte[] bytes, String type, List<String> links) {
		this.bytes = bytes;
		this.type = type;
		this.links = links;
	}

	/**
	 * @param bytes the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<String> links) {
		this.links = links;
	}

	/**
	 * @return the links
	 */
	public List<String> getLinks() {
		return links;
	}
}
