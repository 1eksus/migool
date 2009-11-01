package migool.sm.generator.field;

import migool.sm.Field;

/**
 * 
 * @author Denis Migol
 * 
 */
public class BruteforceFieldGenerator extends FieldGeneratorBase {

	public BruteforceFieldGenerator(Field field) {
		super(field);
	}

	@Override
	public byte[] next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		//IFieldGenerator g = new BruteforceFieldGenerator(new Field());
	}
}
