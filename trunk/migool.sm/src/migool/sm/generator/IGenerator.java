package migool.sm.generator;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface IGenerator {
	/**
	 * Gets count of variants
	 * 
	 * @return
	 */
	public long getCount();

	public boolean hasNext();
	
	public void reset();
}
