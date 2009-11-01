import static migool.sm.Field.*;

import java.util.Arrays;

import migool.sm.generator.symbols.ISymbolsGenerator;
import migool.sm.generator.symbols.SymbolsGeneratorBase;

/**
 * 
 * @author Denis Migol
 *
 */
public class Test {
	public static final byte[] TEST_FIELD = new byte[] {
		DONE, DONE, DONE, DONE, DONE, DONE, DONE, DONE, // 1
		DONE, DONE, DONE, DONE, DONE, MINE, DONE, DONE, // 2
		DONE, DONE, DONE, DONE, DONE, MINE, NONE, NONE, // 3
		DONE, DONE, DONE, DONE, DONE, MINE, NONE, NONE, // 4
		DONE, DONE, DONE, DONE, DONE, MINE, DONE, DONE, // 5
		MINE, MINE, DONE, DONE, DONE, DONE, DONE, DONE, // 6
		NONE, MINE, DONE, DONE, DONE, DONE, DONE, DONE, // 7
		NONE, MINE, DONE, DONE, DONE, DONE, MINE, DONE, // 8
		};
	public static final String TEST_MD5 = "f3c98666bc6594f1f86ad7c11c935f53";
	public static final char[] TEST_CHARS = SymbolsGeneratorBase.createRandomOrder(ISymbolsGenerator.CHARS);
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(SymbolsGeneratorBase.createRandomOrder("1234".toCharArray()));
		System.out.println(Arrays.toString(TEST_CHARS));
	}
}
