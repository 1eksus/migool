package migool.tor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author Denis Migol
 * 
 */
public class TorClient {

	public static final String DEFAULT_CONTROL_HOST = "127.0.0.1";
	public static final int DEFAULT_CONTROL_PORT = 9051;

	public static final String DEFAULT_HOST = "127.0.0.1";
	public static final int DEFAULT_PORT = 8118;

	private String controlHost = DEFAULT_CONTROL_HOST;
	private int controlPort = DEFAULT_CONTROL_PORT;

	private String host = DEFAULT_HOST;
	private int port = DEFAULT_PORT;

	/**
	 * 
	 * @param response
	 * @return
	 */
	private static final int getCode(final String response) {
		return Integer.parseInt(response.split(" ", 2)[0]);
	}

	/**
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws TorClientException
	 */
	public void newIdentity() throws UnknownHostException, IOException, TorClientException {
		Socket socket = new Socket(controlHost, controlPort);
		final Writer out = new OutputStreamWriter(socket.getOutputStream());
		final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		out.write("AUTHENTICATE \r\n");
		out.flush();
		if (getCode(in.readLine()) != 250) {
			throw new TorClientException();
		}

		// send the request to for new identity
		out.write("signal NEWNYM\r\n");
		out.flush();
		if (getCode(in.readLine()) != 250) {
			throw new TorClientException();
		}

		socket.close();
	}

	/**
	 * @param controlHost
	 *            the controlHost to set
	 */
	public void setControlHost(String controlHost) {
		this.controlHost = controlHost;
	}

	/**
	 * @return the controlHost
	 */
	public String getControlHost() {
		return controlHost;
	}

	/**
	 * @param controlPort
	 *            the controlPort to set
	 */
	public void setControlPort(int controlPort) {
		this.controlPort = controlPort;
	}

	/**
	 * @return the controlPort
	 */
	public int getControlPort() {
		return controlPort;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
}
