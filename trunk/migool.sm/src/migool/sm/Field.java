package migool.sm;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Field {
	public static final byte NONE = 0;
	public static final byte MINE = 1;
	public static final byte DONE = 2;

	private byte[] bytes;
	private final int count;

	private int width = 8;
//	private int height = 8;

	public Field() {
		this(12);
	}

	public Field(int count) {
		this(new byte[8 * 8], count);
	}

	public Field(byte[] bytes, int count) {
		this.bytes = bytes;
		this.count = count;
	}

	public void set(int row, int col, byte b) {
		this.bytes[row * width + col] = b;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public int getCount() {
		return count;
	}
}
