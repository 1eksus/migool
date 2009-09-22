package migool.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {
	public static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	public static String getMD5hash(String source) {
		try {
			byte[] dataToHash = source.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(dataToHash, 0, dataToHash.length);
			byte[] hash = md.digest();
			return hexStringFromBytes(hash);
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String hexStringFromBytes(byte[] b) {
		String hex = "";
		int msb;

		int lsb = 0;
		int i;
		// MSB maps to idx 0
		for (i = 0; i < b.length; i++) {
			msb = ((int) b[i] & 0x000000FF) / 16;

			lsb = ((int) b[i] & 0x000000FF) % 16;
			hex = hex + HEX_CHARS[msb] + HEX_CHARS[lsb];
		}
		return (hex);
	}
	
	public static void main(String[] args) {
		// remixmid 27395111
		// remixemail xxxzone.ucoz.ru%40mail.ru
		// remixpass 42881e343df3740fe7a882a8f03d2295
		// remixsid 3740068a0be831e4774a3a2cb64c38869f38e344fce0bc3003cd9a1a
		System.out.println(getMD5hash("xxxzone.ucoz.ru@mail.ru"));
		System.out.println(getMD5hash("42881e343df3740fe7a882a8f03d2295"));
		System.out.println(getMD5hash("Z3tCd2fw"));
		System.out.println(getMD5hash("27395111"));
	}
}
