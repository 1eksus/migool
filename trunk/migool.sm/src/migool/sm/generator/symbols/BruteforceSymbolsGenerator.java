package migool.sm.generator.symbols;

/**
 * 
 * @author Denis Migol
 * 
 */
public class BruteforceSymbolsGenerator extends SymbolsGeneratorBase {

	private final int length;
	private final int count;
	private final char[] ret;
	private final int[] ind;
	
	private int pos;

	public BruteforceSymbolsGenerator(char[] symbols, int size) {
		super(symbols, size);
		length = symbols.length;
		count = (int) Math.pow(length, size);
		ret = new char[size];
		ind = new int[size];
		reset();
	}
	
	private void reset() {
		char[] symbols = this.symbols;
		int[] ind = this.ind;
		for (int i = 0; i < size; i++) {
			ind[i] = 0;
			ret[i] = symbols[0];
		}
		pos = 0;
	}

	@Override
	public String next() {
		String next = new String(ret);
		char[] symbols = this.symbols;
		char end = symbols[length - 1];
		for (pos = size - 1; pos >= 0 && ret[pos] == end; pos--) {
			ind[pos] = 0;
			ret[pos] = symbols[0];
		}
		if (pos >= 0) {
			ret[pos] = symbols[++ind[pos]];
		}
		return next;
	}

	@Override
	public long getCount() {
		return count;
	}

	@Override
	public boolean hasNext() {
		return pos >= 0;
	}

	public static void main(String[] args) {
		ISymbolsGenerator g = new BruteforceSymbolsGenerator("1234".toCharArray(), 3);
		System.out.println(g.getCount());
		while (g.hasNext()) {
			System.out.println(g.next());
		}
	}
}
