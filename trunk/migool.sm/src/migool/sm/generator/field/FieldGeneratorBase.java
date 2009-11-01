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
