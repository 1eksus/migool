package migool.util;

import java.io.File;
import java.io.FileFilter;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class FileUtil {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private FileUtil() {
	}

	/**
	 * file.isDirectory()
	 */
	public static final FileFilter DIRECTORY_FILE_FILTER = new FileFilter() {
		@Override
		public boolean accept(final File file) {
			return file.isDirectory();
		}
	};

	/**
	 * !file.isDirectory()
	 */
	public static final FileFilter NOT_DIRECTORY_FILE_FILTER = new FileFilter() {
		@Override
		public boolean accept(final File file) {
			return !file.isDirectory();
		}
	};

	/**
	 * 
	 * @return
	 */
	public static File getCwd() {
		return new File("");
	}

	/**
	 * 
	 * @param dir
	 */
	public static void mkDir(final File dir) {
		if (dir != null) {
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
		}
	}

	/**
	 * 
	 * @param file
	 */
	public static void delete(final File file) {
		if (file != null) {
			if (file.isDirectory()) {
				for (final File f : file.listFiles()) {
					delete(f);
				}
			}
			file.delete();
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String getExtension(final File file) {
		String ext = null;
		final String s = file.getName();
		final int i = s.lastIndexOf('.');

		if (file.isDirectory())
			ext = null;
		else if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
