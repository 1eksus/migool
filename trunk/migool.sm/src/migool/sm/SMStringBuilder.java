package migool.sm;

/**
 * 
 * @author Denis Migol
 *
 */
public class SMStringBuilder {

	private StringBuilder sb = new StringBuilder();

	/**
	 * 
	 */
	public SMStringBuilder() {
	}

	public String build() {
		return sb.toString();
	}
}
