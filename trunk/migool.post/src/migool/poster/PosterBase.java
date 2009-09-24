package migool.poster;

/**
 * 
 * @author Denis Migol
 *
 */
public abstract class PosterBase implements IPoster {
	protected final String host;

	public PosterBase(String host) {
		this.host = host;
	}
}
