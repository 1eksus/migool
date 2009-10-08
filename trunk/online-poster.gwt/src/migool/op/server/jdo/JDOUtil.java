package migool.op.server.jdo;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import migool.op.server.jdo.persist.HostConfig;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class JDOUtil {
	private JDOUtil() {
	}
	
	/**
	 * The allowed maximum entity size (1MB) which is limited by Google
	 * datastore.
	 * <p>
	 * See {@link http://code.google.com/intl/en/appengine/docs/java/datastore/overview.html#Quotas_and_Limits}
	 * </p>
	 */
	public final static int ENTITY_SIZE = 1024000; // 1MB

	/**
	 * 
	 * @param host
	 * @return
	 */
	public static final HostConfig getHostConfigByHost(String host) {
		HostConfig ret = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			ret = pm.getObjectById(HostConfig.class, host);
		} catch (JDOObjectNotFoundException e) {
		} finally {
			pm.close();
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public static final boolean deleteHostConfigByHost(String host) {
		boolean ret = false;
		HostConfig hostConfig = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			hostConfig = pm.getObjectById(HostConfig.class, host);
			pm.deletePersistent(hostConfig);
			ret = true;
		} catch (JDOObjectNotFoundException e) {
		} finally {
			pm.close();
		}
		return ret;
	}

	/**
	 * 
	 * @param hostConfig
	 */
	public static final void addHostConfig(HostConfig hostConfig) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(hostConfig);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}
