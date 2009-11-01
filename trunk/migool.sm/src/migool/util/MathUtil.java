package migool.util;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class MathUtil {
	private MathUtil() {
	}

	public static final long factorial(int x) {
		if (x < 0) {
			throw new ArithmeticException("x < 0");
		}
		if (x == 0 || x == 1) {
			return 1;
		}
		long ret = 1;
		for (int i = 2; i <= x; i++) {
			ret *= i;
		}
		return ret;
	}

	public static final long A(int n, int k) {
		long up = 1;
		for (int i = n - k + 1; i <= n; i++) {
			up *= i;
		}
		return up / factorial(k);
	}
}
