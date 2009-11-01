package migool.sm;

import java.util.Arrays;

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
	private final int minesCount;

	private int width = 8;
	private int height = 8;

	public Field() {
		this(12);
	}

	public Field(int minesCount) {
		this(new byte[8 * 8], minesCount);
	}

	public Field(byte[] bytes) {
		this(bytes, 12);
	}

	public Field(byte[] bytes, int minesCount) {
		this.bytes = bytes;
		this.minesCount = minesCount;
	}

	public void set(int row, int col, byte b) {
		this.bytes[row * width + col] = b;
	}

	public byte[] getBytes() {
		return Arrays.copyOf(bytes, bytes.length);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSize() {
		return bytes.length;
	}

	public int getMinesCount() {
		return minesCount;
	}

	public int getCount(byte type) {
		byte[] bytes = this.bytes;

		int ret = 0;

		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == type) {
				ret++;
			}
		}
		return ret;
	}
}
