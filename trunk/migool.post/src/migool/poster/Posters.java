package migool.poster;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import migool.poster.host._4erda4okCom;

/**
 * 
 * @author Denis Migol
 *
 */
public final class Posters {
	private Posters() {
	}

	private static final TreeMap<String, IPoster> set = new TreeMap<String, IPoster>();
	private static final ArrayList<String> hosts;
	private static final ArrayList<IPoster> posters;

	static {
		put(set, new _4erda4okCom());
		
		hosts = new ArrayList<String>(set.keySet());
		posters = new ArrayList<IPoster>(set.values());
	}
	
	/**
	 * 
	 * @param set
	 * @param poster
	 */
	private static final void put(TreeMap<String, IPoster> set, IPoster poster) {
		set.put(poster.getHost(), poster);
	}

	/**
	 * 
	 * @return
	 */
	public static final TreeMap<String, IPoster> get() {
		return set;
	}

	/**
	 * 
	 * @return
	 */
	public static final List<IPoster> getPosters() {
		return Posters.posters;
	}

	/**
	 * 
	 * @return
	 */
	public static final List<String> getHosts() {
		return Posters.hosts;
	}
}
