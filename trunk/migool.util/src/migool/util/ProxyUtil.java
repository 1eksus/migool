/**
 * 
 */
package migool.util;

/**
 * @author Denis Migol
 *
 */
public final class ProxyUtil {
	private ProxyUtil() {
	}

	/**
	 * 
	 * @author Denis Migol
	 *
	 */
	public static class Proxy {
		private final String host;
		private final int port;

		public Proxy(String host, int port) {
			this.host = host;
			this.port = port;
		}

		public String getHost() {
			return host;
		}

		public int getPort() {
			return port;
		}

		@Override
		public String toString() {
			return "Proxy [host=" + host + ", port=" + port + "]";
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static Proxy parse(String string) {
		String s = string.trim();
		int pos = 0;
		if ((pos = s.indexOf(':', 2)) > -1) {
			try {
				String host = s.substring(0,pos).trim();
				int port = Integer.parseInt(s.substring(pos+1));
				
				return new Proxy(host, port);
			} catch (Exception e) {
			}
		}
		return null;
	}
}
