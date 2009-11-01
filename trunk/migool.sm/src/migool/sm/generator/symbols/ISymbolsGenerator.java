package migool.sm.generator.symbols;

import migool.sm.generator.IGenerator;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ISymbolsGenerator extends IGenerator {
	char[] CHARS = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	String next();
}
