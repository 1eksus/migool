package migool.ot.server.jdo.serializable;

import java.io.Serializable;

/**
 * 
 * @author Denis Migol
 * 
 */
public class GrabHostConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4328457825165443021L;

	private String host;
	private int grabCount;

	private String lastPage;
	private String lastId;

	/**
	 * @param host the host to set
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
	 * @param grabCount the grabCount to set
	 */
	public void setGrabCount(int grabCount) {
		this.grabCount = grabCount;
	}

	/**
	 * @return the grabCount
	 */
	public int getGrabCount() {
		return grabCount;
	}

	/**
	 * @param lastPage the lastPage to set
	 */
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return the lastPage
	 */
	public String getLastPage() {
		return lastPage;
	}

	/**
	 * @param lastId the lastId to set
	 */
	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

	/**
	 * @return the lastId
	 */
	public String getLastId() {
		return lastId;
	}
}
