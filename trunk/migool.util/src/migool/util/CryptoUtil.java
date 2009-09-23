package migool.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class CryptoUtil {
	public static final String MD5 = "MD5";
	public static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
	'f' };

	private CryptoUtil() {
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final String getMD5hash(String source) {
		try {
			byte[] dataToHash = source.getBytes();
			MessageDigest md = MessageDigest.getInstance(MD5);
			md.update(dataToHash, 0, dataToHash.length);
			return toHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public static final String toHexString(byte[] b) {
		//String hex = "";
		StringBuffer hex = new StringBuffer();
		int msb;

		int lsb = 0;
		int i;
		// MSB maps to idx 0
		for (i = 0; i < b.length; i++) {
			msb = ((int) b[i] & 0x000000FF) / 16;
			lsb = ((int) b[i] & 0x000000FF) % 16;
			//hex = hex + HEX_CHARS[msb] + HEX_CHARS[lsb];
			hex.append(HEX_CHARS[msb] + HEX_CHARS[lsb]);
		}
		//return (hex);
		return hex.toString();
	}
}
