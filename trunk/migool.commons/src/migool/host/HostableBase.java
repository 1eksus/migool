package migool.host;

/**
 * 
 * @author Denis Migol
 * 
 */
public class HostableBase implements Hostable {

	protected final String host;

	/**
	 * 
	 * @param host
	 */
	protected HostableBase(final String host) {
		this.host = host;
	}

	@Override
	public String getHost() {
		return host;
	}

}
