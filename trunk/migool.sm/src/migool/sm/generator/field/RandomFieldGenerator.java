package migool.sm.generator.field;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import migool.sm.Field;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RandomFieldGenerator extends FieldGeneratorBase {

	public RandomFieldGenerator(Field field) {
		super(field);
	}

	@Override
	protected byte[] next(byte[] noneBytes, int minesCount) {
		final int size = noneBytes.length;
		System.out.println(minesCount);
		final Random r = new Random();
		final Set<Integer> indexes = new HashSet<Integer>();
		int next;
		while (minesCount > 0) {
			next = r.nextInt(size);
			if (!indexes.contains(next)) {
				indexes.add(next);
				noneBytes[next] = Field.MINE;
				minesCount--;
			}
		}
		return noneBytes;
	}


	@Override
	public long getCount() {
		// return (long) (1.0 / 0.0);
		// 9223372036854775807
		return Long.MAX_VALUE;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	public static void main(String[] args) {
		Field field = new Field(12);
		//System.out.println(Arrays.toString(field.getBytes()));
		for (int i = 0; i < 5; i++) {
			field.set(0, i, Field.MINE);
		}
		IFieldGenerator g = new RandomFieldGenerator(field);
		for (int i = 0; i < 10; i++) {
			byte[] b = g.next();
			System.out.println((new Field(b)).getCount(Field.MINE) + ": " + Arrays.toString(b));
		}
		//System.out.println(Arrays.toString(field.getBytes()));
	}
}
