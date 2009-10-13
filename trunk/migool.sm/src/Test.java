import static migool.util.CryptoUtil.*;

/**
 * 
 * @author Denis Migol
 *
 */
public class Test {
	//private static final char[] CHARS = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final char[] CHARS = "12".toCharArray();
	
	private static final String toString(int[] indxs) {
		StringBuilder sb = new StringBuilder();
		int count = indxs.length;
		int i = 0;
		for (i = 0; i < count; i++) {
			//System.out.println(indxs[i]);
			sb.append(CHARS[indxs[i]]);
		}
		return sb.toString();
	}

	private static final String find(String prefix, String md5) {
		char[] chars = CHARS;
		int length = chars.length;
		int i = 0;
		int j = 0;
		int k = 0;
		String ret = null;
		int count = 3; // number of symbols to find
		int[] ind = new int[count];
		for (i = 0; i < count; i++) { // for each index
			for (ind[i] = 0; ind[i] < length; ind[i]++) {
				for (j = 0; j < i; j++) {
					System.out.println(toString(ind));
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		String MD5 = "53ec5c68fecb3dc74623eafcff18fa28";
		System.out.println(getMD5hash("(1*345678)_(**345678)_(**345678)_(12345*78)_(12345*7*)_(12345**8)_(12345*78)_(12*45678)_game_rxAf2fnS"));
		String prefix = "(1*345678)_(**345678)_(**345678)_(12345*78)_(12345*7*)_(12345**8)_(12345*78)_(12*45678)_game_";

		long start = System.currentTimeMillis();
		String find = find(prefix, MD5);
		long stop = System.currentTimeMillis();

		System.out.println(find);
		System.out.println();
		System.out.println("time: " + (stop - start));
	}
}
