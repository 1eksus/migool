package migool.sm.generator.symbols;

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
