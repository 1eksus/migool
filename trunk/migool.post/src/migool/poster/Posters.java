package migool.poster;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Denis Migol
 *
 */
public class Posters {
	private static final ArrayList<IPoster> posters;

	static {
		// TODO
		posters = new ArrayList<IPoster>();
		Package p = Package.getPackage(Posters.class.getPackage().getName() + ".host");
	}

	public static final List<IPoster> get() {
		return Posters.posters;
	}
}
