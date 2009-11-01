package migool.sm.generator.field;

import migool.sm.Field;

/**
 * 
 * @author Denis Migol
 * 
 */
public abstract class FieldGeneratorBase implements IFieldGenerator {

	protected final Field field;

	public FieldGeneratorBase(Field field) {
		this.field = field;
	}

	protected byte[] fillNoneBytes(byte[] filledBytes) {
		byte[] bytes = field.getBytes();
		int length = bytes.length;
		int size = filledBytes.length;
		int pos = 0;
		for (int i = 0; i < length && pos < size; i++) {
			if (bytes[i] == Field.NONE) {
				bytes[i] = filledBytes[pos++];
			}
		}
		return bytes;
	}

	@Override
	public byte[] next() {
		final byte[] ret = new byte[field.getCount(Field.NONE)];
		int length = ret.length;
		for (int i = 0; i < length; i++) {
			ret[i] = Field.NONE;
		}
		return fillNoneBytes(next(ret, field.getMinesCount() - field.getCount(Field.MINE)));
	}

	protected abstract byte[] next(byte[] noneBytes, int minesCount);

	// @Override
	// public byte next() {
	// throw new UnsupportedOperationException();
	// }
	//
	// @Override
	// public long getCount() {
	// throw new UnsupportedOperationException();
	// }
	//
	// @Override
	// public boolean hasNext() {
	// throw new UnsupportedOperationException();
	// }

}
