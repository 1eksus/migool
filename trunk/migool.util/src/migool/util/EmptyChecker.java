/**
 * 
 */
package migool.util;

/**
 * @author Denis Migol
 *
 */
public final class EmptyChecker {

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return obj != null;
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		return (s == null) || (s.length() == 0);
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String s) {
		return (s != null) && (s.length() > 0);
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isNotNullOrEmpty(final String[] strings) {
		return (strings != null) && (strings.length > 0);
	}
}