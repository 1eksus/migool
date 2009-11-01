package migool.sm.generator.field;

import java.util.Arrays;

import migool.sm.Field;
import migool.util.MathUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public class BruteforceFieldGenerator extends FieldGeneratorBase {

	private final int[] ind;
	private boolean isFirst;
	private final int count;

	public BruteforceFieldGenerator(Field field) {
		super(field);

		int k = field.getLeftMinesCount();
		int n = field.getBytes().length;
		count = MathUtil.factorial(n) / (MathUtil.factorial(k) * MathUtil.factorial(n - k));

		ind = new int[k];
		reset();
	}

	private void reset() {
		isFirst = true;
	}

	@Override
	protected byte[] next(byte[] noneBytes, int minesCount) {
		int[] ind = this.ind;
		if (isFirst) {
			isFirst = false;
			for (int i = 0; i < minesCount; i++) {
				ind[i] = i;
			}
		} else {
			int length = noneBytes.length;
			if (ind[minesCount - 1] < length - 1) {
				ind[minesCount - 1]++;
			} else {
				for (int i = minesCount - 2; i >= 0; i--) {
					if (ind[i] < length - minesCount + i) {
						ind[i]++;
						for (int j = i + 1; j < minesCount; j++) {
							ind[j] = ind[j - 1] + 1;
						}
						break;
					}
				}
			}
		}
		for (int i = 0; i < minesCount; i++) {
			noneBytes[ind[i]] = Field.MINE;
		}
		return noneBytes;
	}

	@Override
	public long getCount() {
		return count;
	}

	@Override
	public boolean hasNext() {
		return (ind[0] != (field.getBytes().length - ind.length));
	}

	public static void main(String[] args) {
		IFieldGenerator g = new BruteforceFieldGenerator(new Field(new byte[9], 3));
		// IFieldGenerator g = new BruteforceFieldGenerator(new Field());
		while (g.hasNext())
			System.out.println(Arrays.toString(g.next()));
		System.out.println(g.getCount());
	}
}
