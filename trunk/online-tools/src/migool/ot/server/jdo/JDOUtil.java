package migool.ot.server.jdo;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import migool.ot.client.serializable.PostHostConfig;
import migool.ot.server.jdo.pc.PostHostConfigPC;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class JDOUtil {
	private JDOUtil() {
	}

	/**
	 * 
	 * @param host
	 * @return
	 */
	public static final PostHostConfig getPostHostConfigByHost(String host) {
		PostHostConfig ret = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			PostHostConfigPC configPC = pm.getObjectById(PostHostConfigPC.class, host);
			ret = configPC.getConfig();
		} finally {
			pm.close();
		}
		return ret;
	}

	/**
	 * 
	 * @param host
	 * @return
	 */
	public static final boolean deletePostHostConfigByHost(String host) {
		boolean ret = false;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			PostHostConfigPC configPC = pm.getObjectById(PostHostConfigPC.class, host);
			pm.deletePersistent(configPC);
			ret =  true;
		} finally {
			pm.close();
		}
		return ret;
	}

	/**
	 * 
	 * @param config
	 */
	public static final void addPostHostConfig(PostHostConfig config) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			PostHostConfigPC configPC = new PostHostConfigPC(config);
			tx.begin();
			pm.makePersistent(configPC);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}
