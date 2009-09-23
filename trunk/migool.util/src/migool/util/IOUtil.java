package migool.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 
 * @author Denis Migol
 *
 */
public final class IOUtil {
	private IOUtil() {
	}

	/**
	 * 
	 * @param in
	 * @return
	 */
	public static final byte[] toByteArray(InputStream in) {
		try {
			int length = 0;
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			while ((length = in.available()) > 0) {
				byte[] buf = new byte[length];
				int read = 0;
				while ((read = in.read(buf)) > 0) {
					bo.write(buf, 0, read);
				}
			}
			return bo.toByteArray();
		} catch (Exception e) {
		}
		return null;
	}
}
