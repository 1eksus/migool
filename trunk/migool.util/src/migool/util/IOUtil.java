package migool.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	public static final byte[] toByteArray(InputStream in) throws IOException {
		int length = 0;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		while ((length = in.available()) > 0) {
			byte[] buf = new byte[length];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				bo.write(buf, 0, read);
			}
		}
		in.close();
		return bo.toByteArray();
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static final String toString(InputStream in) throws IOException {
		StringBuilder ret = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			ret.append(line + "\n");
		}
		br.close();
		return ret.toString();
	}
}
