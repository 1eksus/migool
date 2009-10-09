package migool.poster;

import java.util.ArrayList;
import java.util.List;

import migool.poster.host._4erda4okCom;

/**
 * 
 * @author Denis Migol
 *
 */
public final class Posters {
	private Posters() {
	}

	private static final ArrayList<IPoster> posters;

	static {
		posters = new ArrayList<IPoster>();
		posters.add(new _4erda4okCom());
	}

	/**
	 * 
	 * @return
	 */
	public static final List<IPoster> get() {
		return Posters.posters;
	}
}
