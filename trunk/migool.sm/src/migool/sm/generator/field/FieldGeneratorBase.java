package migool.sm.generator.field;

import migool.sm.Field;

/**
 * 
 * @author Denis Migol
 * 
 */
public abstract class FieldGeneratorBase implements IFieldGenerator {

//	protected Field field;
	protected byte[] bytes;

	public FieldGeneratorBase(Field field) {
		this(field.getBytes());
	}
	
	public FieldGeneratorBase(byte[] bytes) {
		this.bytes = bytes;
	}

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
