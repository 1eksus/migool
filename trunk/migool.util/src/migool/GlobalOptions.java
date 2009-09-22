package migool;

import migool.util.EmptyChecker;

/**
 * 
 * @author Denis Migol
 *
 */
public final class GlobalOptions {
	private static String proxyHost = null;
	private static int proxyPort = -1;
	
	/**
	 * Sets proxy server.
	 */
	public static void setProxyServer(String proxyHost, int proxyPort) {
		GlobalOptions.proxyHost = proxyHost;
		GlobalOptions.proxyPort = proxyPort;
	}
	
	/**
	 * 
	 * @param data
	 */
	public static void setProxyServer(String data) {
		try {
			String proxyHost = data.substring(0, data.lastIndexOf(":"));
			if (EmptyChecker.isNullOrEmpty(proxyHost) || proxyHost.equals(data) || proxyHost.equals(data.substring(0, data.length() - 1))) {
				throw new NumberFormatException();
			}
			int proxyPort = Integer.parseInt(data.substring(data.lastIndexOf(":") + 1, data.length()));
			
			setProxyServer(proxyHost, proxyPort);
		} catch (NumberFormatException e) {
		}
	}

	/**
	 * Tests if proxy server are set.
	 * 
	 * @return
	 */
	public static boolean isProxySetted() {
		return EmptyChecker.isNotNullOrEmpty(proxyHost) && (proxyPort > 0);
	}

	/**
	 * Gets proxyHost.
	 * 
	 * @return
	 */
	public static String getProxyHost() {
		return GlobalOptions.proxyHost;
	}

	/**
	 * Gets proxyPort.
	 * 
	 * @return
	 */
	public static int getProxyPort() {
		return GlobalOptions.proxyPort;
	}
}
