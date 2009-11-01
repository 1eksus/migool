import static migool.sm.Field.*;

import java.util.Arrays;

import migool.sm.Field;
import migool.sm.SMStringBuilder;
import migool.sm.generator.field.BruteforceFieldGenerator;
import migool.sm.generator.field.IFieldGenerator;
import migool.sm.generator.symbols.BruteforceSymbolsGenerator;
import migool.sm.generator.symbols.ISymbolsGenerator;
import migool.sm.generator.symbols.SymbolsGeneratorBase;
import migool.util.CryptoUtil;

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
	public static final byte[] TEST_MD5 = CryptoUtil.fromHexString("f3c98666bc6594f1f86ad7c11c935f53");
	public static final char[] TEST_CHARS = SymbolsGeneratorBase.createRandomOrder(ISymbolsGenerator.CHARS);

	public static void main(String[] args) {
//		for (int i = 0; i < 100; i++)
//			System.out.println(SymbolsGeneratorBase.createRandomOrder("1234".toCharArray()));
		System.out.println(Arrays.toString(TEST_CHARS));
		boolean flag = false;
		final Field f = new Field(TEST_FIELD);
		System.out.println(f);

		final IFieldGenerator fg = new BruteforceFieldGenerator(f);
		long fgCount = fg.getCount();
		System.out.println("fg.count: " + fgCount);

		final ISymbolsGenerator sg = new BruteforceSymbolsGenerator(TEST_CHARS, 8);
		long sgCount = sg.getCount();
		System.out.println("sg.count: " + sgCount);

		String c = fgCount * sgCount + "";
		System.out.println("total count: " + c + " (" + c.length() + " digits)");

		byte[] b = null;
		String s = null;
		byte[] md5 = null;

		for (int i = 0; i < fgCount && !flag; i++) {
			b = fg.next();
			System.out.println(Field.toString(b));
//			for (int j = 0; j < sgCount && !flag; j++) {
//			s = sg.next();
//			s = SMStringBuilder.toString(b, s);
//			md5 = CryptoUtil.getMD5hash(s);
//			flag = TEST_MD5.equals(md5);
//		}
			int iters = 100;
			long iterCount = sgCount / iters;
			long mod = sgCount % 100;
			for (int j = 0; j < iters; j++) {
				long start = System.currentTimeMillis();
				for (long k = 0; k < iterCount; k++) {
					s = sg.next();
					s = SMStringBuilder.toString(b, s);
					//md5 = CryptoUtil.getMD5hash(s);
					md5 = CryptoUtil.getMD5hash(s.getBytes());
					//if (TEST_MD5.equals(md5)) {
					if (Arrays.equals(TEST_MD5, md5)) {
						System.out.println(s);
						return;
					}
				}
				System.out.println(j + "% complete, time: " + (System.currentTimeMillis() - start) + "ms");
			}
			for (long k = 0; k < mod; k++) {
				s = sg.next();
				s = SMStringBuilder.toString(b, s);
				md5 = CryptoUtil.getMD5hash(s.getBytes());
				//if (TEST_MD5.equals(md5)) {
				if (Arrays.equals(TEST_MD5, md5)) {
					System.out.println(s);
					return;
				}
			}
			System.out.println();
		}
		System.out.println(md5);
		System.out.println("NOT found :(");
	}
}
