package migool.host;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import migool.host.auth.LoginPassword;
import migool.host.auth.LoginResponse;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IHostClient {
	public LoginResponse login(LoginPassword lp) throws ClientProtocolException, IOException;
	public String getHost();
}