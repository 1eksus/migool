package migool.util;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Sleeper {
	public static final int SEC_MILLIS = 1000;
	public static final int MIN_MILLIS = 60 * SEC_MILLIS;
	public static final int HOUR_MILLIS = 60 * MIN_MILLIS;

	private long startSleep = System.currentTimeMillis();

	public Sleeper() {
	}

	public void startSleep() {
		startSleep = System.currentTimeMillis();
	}

	public void endSleep(final long millis) throws InterruptedException {
		final long sleep = millis - System.currentTimeMillis() + startSleep;
		if (sleep > 0) {
			Thread.sleep(sleep);
		}
	}
}
