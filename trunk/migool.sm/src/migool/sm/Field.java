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

	private int width = 8;
	private int height = 8;

	public Field() {
		this.bytes = new byte[width * height];
	}

	public Field(byte[] bytes) {
		this.bytes = bytes;
	}

	public void set(int row, int col, byte b) {
		this.bytes[row*8 + col] = b;
	}

	public byte[] getBytes() {
		return bytes;
	}
}
