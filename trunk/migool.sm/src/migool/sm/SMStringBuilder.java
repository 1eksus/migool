package migool.sm;

import migool.sm.generator.field.RandomFieldGenerator;
import migool.sm.generator.symbols.RandomSymbolsGenerator;
import migool.util.CryptoUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public class SMStringBuilder {

	private static final String GAME = "game_";
	private StringBuilder sb = new StringBuilder();

	/**
	 * 
	 */
	public SMStringBuilder() {
	}

	public void setField(Field field) {
		// String symbols = "";
		// if (sb.lastIndexOf(GAME) > -1) {
		// symbols = sb.substring(sb.lastIndexOf(GAME), sb.length());
		// }
		sb.setLength(0);
		byte[] b = field.getBytes();
		int width = field.getWidth();
		int height = field.getHeight();
		for (int i = 0; i < height; i++) {
			sb.append("(");
			for (int j = 0; j < width; j++) {
				sb.append((b[i * width + j] == Field.MINE) ? "*" : (j + 1));
			}
			sb.append(")_");
		}
		sb.append(GAME);
		// sb.append(symbols);
	}

	public void setSymbols(String string) {
		sb.append(string);
	}

	public String build() {
		return sb.toString();
	}

	private static final StringBuilder ret = new StringBuilder();

	public static final String toString(Field field, String symbols) {
		// StringBuilder ret = new StringBuilder();
		ret.setLength(0);
		byte[] b = field.getBytes();
		int width = field.getWidth();
		int height = field.getHeight();
		for (int i = 0; i < height; i++) {
			ret.append("(");
			for (int j = 0; j < width; j++) {
				ret.append((b[i * width + j] == Field.MINE) ? "*" : (j + 1));
			}
			ret.append(")_");
		}
		ret.append(GAME);
		ret.append(symbols);
		return ret.toString();
	}
	
	public static final String toString(byte[] field, String symbols) {
		ret.setLength(0);
		int width = 8;
		int height = 8;
		for (int i = 0; i < height; i++) {
			ret.append("(");
			for (int j = 0; j < width; j++) {
				ret.append((field[i * width + j] == Field.MINE) ? "*" : (j + 1));
			}
			ret.append(")_");
		}
		ret.append(GAME);
		ret.append(symbols);
		return ret.toString();
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// SMStringBuilder b = new SMStringBuilder();
			// b.setField(new Field(new RandomFieldGenerator(new
			// Field()).next()));
			// b.setSymbols("symbols");
			// String string = b.build();
			String string = toString(new Field(new RandomFieldGenerator(new Field()).next()),
					(new RandomSymbolsGenerator()).next());
			// System.out.println(string);
			CryptoUtil.getMD5hash(string);
			// System.out.println(CryptoUtil.getMD5hash(string));
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("time: " + time);
	}
}
