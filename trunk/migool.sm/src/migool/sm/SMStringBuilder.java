package migool.sm;

import migool.sm.generator.field.RandomFieldGenerator;

/**
 * 
 * @author Denis Migol
 * 
 */
public class SMStringBuilder {

	private StringBuilder sb = new StringBuilder();

	/**
	 * 
	 */
	public SMStringBuilder() {
	}

	public void setField(Field field) {
		sb.setLength(0);
		byte[] b = field.getBytes();
		int width = field.getWidth();
		int height = field.getHeight();
		for (int i = 0; i < height; i++) {
			sb.append("(");
			for (int j = 0; j < width; j++) {
				sb.append((b[i * width + j] == Field.MINE) ? "*": (j + 1));
			}
			sb.append(")_");
		}
		sb.append("game_");
	}

	public void setSymbols(String string) {

	}

	public String build() {
		return sb.toString();
	}

	public static void main(String[] args) {
		SMStringBuilder b = new SMStringBuilder();
		b.setField(new Field(new RandomFieldGenerator(new Field()).next()));
		System.out.println(b.build());
	}
}
