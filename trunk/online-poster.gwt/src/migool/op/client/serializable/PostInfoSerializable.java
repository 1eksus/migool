package migool.op.client.serializable;

import java.io.Serializable;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public class PostInfoSerializable implements Serializable {
	public String imageUrl;
	public String paramName;

	public PostInfoSerializable() {
		this(null, null);
	}

	public PostInfoSerializable(String imageUrl, String paramName) {
		this.imageUrl = imageUrl;
		this.paramName = paramName;
	}
}
