package migool.grabbers.proxy;

import java.net.Proxy;
import java.util.Date;

import migool.grabbers.IEntity;
import migool.util.EmptyChecker;

/**
 * 
 * @author Denis Migol
 *
 */
public class ProxyEntity implements IEntity {
	private String host;
	private String port;
	private Proxy.Type type;
	
	private String visible; // transparent, anonymous
	private String country;

	private Date check;

	/**
	 * The constructor.
	 */
	public ProxyEntity() {
		host = "";
		port = "";
	}

	/**
	 * 
	 * @param host
	 * @param port
	 */
	public ProxyEntity(String host, String port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * 
	 * @param hostPort must be "host:port"
	 */
	public ProxyEntity(String hostPort) {
		
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(Date check) {
		this.check = check;
	}

	/**
	 * @return the check
	 */
	public Date getCheck() {
		return check;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Proxy.Type type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public Proxy.Type getType() {
		return type;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(String visible) {
		this.visible = visible;
	}

	/**
	 * @return the visible
	 */
	public String getVisible() {
		return visible;
	}

	/**
	 * Compares this object against the specified object.
	 */
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ProxyEntity))
		    return false;
		
		ProxyEntity p = (ProxyEntity) obj;
		return host.equalsIgnoreCase(p.host) && port.equals(p.port);
	}

	public boolean isDirect() {
		return EmptyChecker.isNullOrEmpty(host) && EmptyChecker.isNullOrEmpty(port);
	}
}
