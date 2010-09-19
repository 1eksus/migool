package migool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class RarUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private RarUtil() {
	}

	/* EXIT VALUES */

	/**
	 * User stopped the process
	 */
	public static final int USER_BREAK = 255;

	/**
	 * Create file error
	 */
	public static final int CREATE_ERROR = 9;

	/**
	 * Not enough memory for operation
	 */
	public static final int MEMORY_ERROR = 8;

	/**
	 * Command line option error
	 */
	public static final int USER_ERROR = 7;

	/**
	 * Open file error
	 */
	public static final int OPEN_ERROR = 6;

	/**
	 * Write to disk error
	 */
	public static final int WRITE_ERROR = 5;

	/**
	 * Attempt to modify an archive previously locked by the 'k' command
	 */
	public static final int LOCKED_ARCHIVE = 4;

	/**
	 * A CRC error occurred when unpacking
	 */
	public static final int CRC_ERROR = 3;

	/**
	 * A fatal error occurred
	 */
	public static final int FATAL_ERROR = 2;

	/**
	 * Non fatal error(s) occurred
	 */
	public static final int WARNING = 1;

	/**
	 * Successful operation
	 */
	public static final int SUCCESS = 0;

	/* EXIT VALUES end */

	private static final String PATH_TO_RAR = "C:\\Program Files\\WinRar\\";

	private static final String[] DEFAULT_OPTIONS = new String[] { "-v1m -idcdp" };

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String[] compress(final File file) throws IOException {
		return compress(file, DEFAULT_OPTIONS);
	}

	/**
	 * 
	 * @param file
	 * @param options
	 * @return
	 * @throws IOException
	 */
	public static String[] compress(final File file, final String[] options) throws IOException {
		final StringBuilder rarCmd = new StringBuilder(PATH_TO_RAR + "rar a");
		for (final String option : options) {
			rarCmd.append(' ' + option);
		}
		rarCmd.append(' ');
		final String filePath = file.getAbsolutePath();
		rarCmd.append(filePath + ".rar" + ' ' + filePath);

		final String fileNamesRegex = RegexUtil.replaceToRegex(filePath) + "\\.part[\\d]{0,3}" + "\\.rar";
		System.out.println(fileNamesRegex);

		final Process process = Runtime.getRuntime().exec(rarCmd.toString());
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			if (!"".equals(line)) {
				System.out.println(line);
				sb.append(line);
			}
		}
		try {
			System.out.println(process.waitFor());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Regex(sb.toString(), fileNamesRegex).getMatchesAsArray();
	}

	public static void main(String[] args) throws Exception {
		final String[] ret = compress(new File("d:/test.rar/24_Windows_XP.doc"));
		System.out.println("----------------------------------");
		System.out.println(Arrays.toString(ret));
	}
}
