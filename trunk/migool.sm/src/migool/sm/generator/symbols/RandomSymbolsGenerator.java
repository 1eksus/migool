package migool.sm.generator.symbols;

import java.util.Random;

/**
 * 
 * @author Denis Migol
 * 
 */
public class RandomSymbolsGenerator extends SymbolsGeneratorBase {

	public RandomSymbolsGenerator() {
		this(CHARS, 8);
	}

	public RandomSymbolsGenerator(char[] symbols, int size) {
		super(symbols, size);
	}

	@Override
	public String next() {
		StringBuilder ret = new StringBuilder();
		int size = this.size;
		int length = symbols.length;
		Random r = new Random();
		char[] symbols = this.symbols;
		for (int i = 0; i < size; i++) {
			ret.append(symbols[r.nextInt(length)]);
		}
		return ret.toString();
	}

	@Override
	public long getCount() {
		return (long) Math.pow(symbols.length, size);
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
