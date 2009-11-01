package migool.sm.generator.symbols;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author Denis Migol
 * 
 */
public abstract class SymbolsGeneratorBase implements ISymbolsGenerator {

	protected final char[] symbols;
	protected final int size;

	public SymbolsGeneratorBase(char[] symbols, int size) {
		this.symbols = symbols;
		this.size = size;
	}

	public static final char[] createRandomOrder(char[] chars) {
		Set<Integer> indexes = new HashSet<Integer>();
		int size = chars.length;
		char[] ret = new char[size];
		Random r = new Random();
		int next;
		int count = size;
		while (count > 0) {
			next = r.nextInt(size);
			if (!indexes.contains(next)) {
				indexes.add(next);
				ret[--count] = chars[next];
			}
		}
		return ret;
	}

	// @Override
	// public String next() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public long getCount() {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public boolean hasNext() {
	// // TODO Auto-generated method stub
	// return false;
	// }

}
