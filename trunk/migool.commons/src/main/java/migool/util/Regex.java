package migool.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Denis Migol
 *
 */
public class Regex {
	public static boolean matches(Object str, Pattern pat) {
		return new Regex(str, pat).matches();
	}

	public static boolean matches(Object page, String string) {
		return new Regex(page, string).matches();
	}

	private Matcher matcher;

	public Regex(Matcher matcher) {
		if (matcher == null) {
			return;
		}
		this.matcher = matcher;
	}

	/**
	 * Regexp auf ein Objekt (Beim Objekt wird toString aufgerufen)
	 * 
	 * @param data
	 * @param pattern
	 */
	public Regex(Object data, Pattern pattern) {
		this(data.toString(), pattern);
	}

	/**
	 * Regexp auf ein Objekt (Beim Objekt wird toString aufgerufen)
	 * 
	 * @param data
	 * @param pattern
	 */
	public Regex(Object data, String pattern) {
		this(data.toString(), pattern);
	}

	/**
	 * Regexp auf ein Objekt (Beim Objekt wird toString aufgerufen)
	 * 
	 * @param data
	 * @param pattern
	 * @param flags
	 *            flags für den Pattern z.B. Pattern.CASE_INSENSITIVE
	 */
	public Regex(Object data, String pattern, int flags) {
		this(data.toString(), pattern, flags);
	}

	public Regex(String data, Pattern pattern) {
		if (data == null || pattern == null) {
			return;
		}
		matcher = pattern.matcher(data);
	}

	public Regex(String data, String pattern) {
		if (data == null || pattern == null) {
			return;
		}
		matcher = Pattern.compile(pattern,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(data);

	}

	public Regex(String data, String pattern, int flags) {
		if (data == null || pattern == null) {
			return;
		}
		matcher = Pattern.compile(pattern, flags).matcher(data);
	}

	/**
	 * gibt die Anzahl der Treffer zurück
	 */
	public int count() {
		if (matcher == null) {
			return 0;
		}
		matcher.reset();
		int c = 0;
		Matcher matchertmp = matcher;
		while (matchertmp.find()) {
			c++;
		}
		return c;
	}

	public String getMatch(int group) {
		if (matcher == null) {
			return null;
		}
		Matcher matchertmp = matcher;
		matcher.reset();
		if (matchertmp.find()) {
			return matchertmp.group(group + 1);
		}

		return null;

	}

	/**
	 * gibt den matcher aus
	 */
	public Matcher getMatcher() {
		matcher.reset();
		return matcher;
	}

	/**
	 * Gibt alle Treffer eines Matches in einem 2D array aus
	 */
	public String[][] getMatches() {
		if (matcher == null) {
			return null;
		}
		Matcher matchertmp = matcher;
		matcher.reset();
		ArrayList<String[]> ar = new ArrayList<String[]>();
		while (matchertmp.find()) {
			int c = matchertmp.groupCount();
			int d = 1;
			String[] group;
			if (c == 0) {
				group = new String[c + 1];
				d = 0;
			} else {
				group = new String[c];
			}

			for (int i = d; i <= c; i++) {
				group[i - d] = matchertmp.group(i);
			}
			ar.add(group);
		}
		return ar.toArray(new String[][] {});
	}
	
	public String[] getMatchesAsArray() {
		if (matcher == null) {
			return null;
		}
		Matcher matchertmp = matcher;
		matcher.reset();
		ArrayList<String> ar = new ArrayList<String>();
		while (matchertmp.find()) {
			int c = matchertmp.groupCount();
			int d = 1;
			String[] group;
			if (c == 0) {
				group = new String[c + 1];
				d = 0;
			} else {
				group = new String[c];
			}

			for (int i = d; i <= c; i++) {
				group[i - d] = matchertmp.group(i);
			}
			ar.addAll(Arrays.asList(group));
		}
		return ar.toArray(new String[ar.size()]);		
	}

	public boolean matches() {
		matcher.reset();
		return matcher.find();
	}

	/**
	 * sets the matcher
	 */
	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	@Override
	public String toString() {
		String ret = "";
		String[][] match = getMatches();
		for (int i = 0; i < match.length; i++) {
			for (int j = 0; j < match.length; j++) {
				ret += "match[" + i + "][" + j + "]=" + match[i][j]
						+ System.getProperty("line.separator");
			}
		}
		matcher.reset();
		return ret;
	}

	public String getMatch(int entry, int group) {
		if (matcher == null) {
			return null;
		}
		Matcher matchertmp = matcher;
		matcher.reset();
		entry++;
		int groupCount = 0;
		while (matchertmp.find()) {
			if (groupCount == group) {
				return matchertmp.group(entry);
			}

			groupCount++;
		}
		return null;
	}
	
	public String[] getColumn(int x) {
		if (matcher == null) {
			return null;
		}
		x++;
		Matcher matchertmp = matcher;
		matcher.reset();

		ArrayList<String> ar = new ArrayList<String>();
		while (matchertmp.find()) {
			ar.add(matchertmp.group(x));
		}
		return ar.toArray(new String[ar.size()]);
	}

	public String[] getRow(int y) {
		if (matcher == null) {
			return null;
		}
		Matcher matchertmp = matcher;
		matcher.reset();
		int groupCount = 0;
		while (matchertmp.find()) {
			if (groupCount == y) {
				int c = matchertmp.groupCount();

				String[] group = new String[c];

				for (int i = 1; i <= c; i++) {
					group[i - 1] = matchertmp.group(i);
				}
				return group;
			}
			groupCount++;
		}
		return null;
	}
}
