package migool.auth;

import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ILoginService {
	/**
	 * 
	 * @param host
	 * @return
	 */
	List<Login> get(String host);

	/**
	 * 
	 * @param host
	 * @param login
	 */
	void put(String host, List<Login> logins);
}
