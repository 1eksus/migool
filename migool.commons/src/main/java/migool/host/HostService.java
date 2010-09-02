package migool.host;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Denis Migol
 * 
 * @param <H>
 */
public class HostService<H> {
	private final Map<String, H> hosters = new HashMap<String, H>();
	private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

	protected void register(final String host, final Class<?> clazz) {
		classes.put(host, clazz);
	}

	// // @SuppressWarnings("unchecked")
	// protected void register(String host, H hoster) {
	// hosters.put(host, hoster);
	// // classes.put(host, (Class<H>) hoster.getClass());
	// }

	@SuppressWarnings("unchecked")
	public H get(final String host) {
		H ret = hosters.get(host);
		if (ret == null) {
			try {
				ret = ((Class<H>) classes.get(host)).newInstance();
				hosters.put(host, ret);
			} catch (final Exception e) {
			}
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public Set<String> getHosts() {
		// return hosters.keySet();
		return classes.keySet();
	}
}
