package migool.ot.server.hosts;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class Posters {
	private Posters() {
	}
	
	public static final List<String> posters;
	
	static {
		posters = new ArrayList<String>(2);
		posters.add("pornohouse.net.ru");
		posters.add("xxxzone.org.ru");
	}
}
