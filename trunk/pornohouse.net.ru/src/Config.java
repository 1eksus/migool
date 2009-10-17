/**
 * 
 * @author Denis Migol
 *
 */
public class Config {
	public String username;
	public String password;
	public String url;
	public String host = "pornohouse.net.ru";

	@Override
	public String toString() {
		return "Config [host=" + host + ", password=" + password + ", url=" + url + ", username=" + username + "]";
	}
}
