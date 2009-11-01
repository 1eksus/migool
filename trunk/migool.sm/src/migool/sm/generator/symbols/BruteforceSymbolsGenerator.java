package migool.sm.generator.symbols;

/**
 * 
 * @author Denis Migol
 *
 */
public class BruteforceSymbolsGenerator extends SymbolsGeneratorBase {
	
	private final int srcLength;
	private final int permutations;

	public BruteforceSymbolsGenerator(char[] symbols, int size) {
		super(symbols, size);
		srcLength = symbols.length;
		permutations = (int) Math.pow(srcLength, size);
		reset();
	}
	
	private int position;
	private int t2;
	private int p1;
	private int al;
	private int p2;

	private void reset() {
		
	}

	@Override
	public String next() {
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
		ISymbolsGenerator g = new BruteforceSymbolsGenerator("1234".toCharArray(), 3);
		while (g.hasNext()) {
			System.out.println(g.next());
		}
	}
}
