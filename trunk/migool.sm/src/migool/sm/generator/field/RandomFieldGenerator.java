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
	public byte[] next() {
		final byte[] ret = field.getBytes();
		final int size = ret.length;
		int count = field.getCount();
		final Set<Integer> indexes = new HashSet<Integer>();
		final Random r = new Random();
		int next;
		while (count >= 0) {
			next = r.nextInt(size);
			if (!indexes.contains(next)) {
				ret[next] = Field.MINE;
				count--;
			}
		}
		return ret;
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
		System.out.println(Arrays.toString(field.getBytes()));
		IFieldGenerator g = new RandomFieldGenerator(new Field(12));
		// System.out.println(g.getCount());
		System.out.println(Arrays.toString(g.next()));
		System.out.println(Arrays.toString(g.next()));
		System.out.println(Arrays.toString(field.getBytes()));
	}
}
