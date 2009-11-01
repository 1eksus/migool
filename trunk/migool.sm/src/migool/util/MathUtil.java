package migool.util;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class MathUtil {
	private MathUtil() {
	}

	public static final int factorial(int x) {
		if (x < 0) {
			throw new ArithmeticException("x < 0");
		}
		if (x == 0 || x == 1) {
			return 1;
		}
		int ret = 1;
		for (int i = 2; i <= x; i++) {
			ret *= i;
		}
		return ret;
	}
}
