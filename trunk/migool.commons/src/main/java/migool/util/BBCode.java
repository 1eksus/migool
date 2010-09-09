package migool.util;

import static migool.util.StringUtil.isNullOrEmpty;

/**
 * @author Denis Migol
 * 
 */
public final class BBCode {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private BBCode() {
	}

	/**
	 * 
	 * @param bbTag
	 * @return
	 */
	public static String createBBBegin(final String bbTag) {
		return isNullOrEmpty(bbTag) ? "" : createBBBeginInternal(bbTag);
	}

	private static String createBBBeginInternal(final String bbTag) {
		return "[" + bbTag + "]";
	}

	/**
	 * 
	 * @param bbTag
	 * @param bbText
	 * @return
	 */
	public static String createBBBegin(final String bbTag, final String bbText) {
		return isNullOrEmpty(bbTag) ? "" : isNullOrEmpty(bbText) ? createBBBeginInternal(bbTag)
				: createBBBeginInternal(bbTag, bbText);
	}

	private static String createBBBeginInternal(final String bbTag, final String bbText) {
		return "[" + bbTag + "=" + bbText + "]";
	}

	/**
	 * 
	 * @param bbTag
	 * @return
	 */
	public static String createBBEnd(final String bbTag) {
		return isNullOrEmpty(bbTag) ? "" : createBBEndInternal(bbTag);
	}

	private static String createBBEndInternal(final String bbTag) {
		return "[/" + bbTag + "]";
	}

	/**
	 * 
	 * @param bbTag
	 * @return
	 */
	public static String createBBCode(final String bbTag) {
		return "[" + bbTag + "]";
	}

	/**
	 * 
	 * @param bb
	 * @param text
	 * @return [bb]text[/bb]
	 */
	public static String createBBCode(final String bbTag, final String text) {
		return (isNullOrEmpty(bbTag) || isNullOrEmpty(text)) ? "" : createBBCodeInternal(bbTag, text);
	}

	private static String createBBCodeInternal(final String bbTag, final String text) {
		return createBBBeginInternal(bbTag) + text + createBBEndInternal(bbTag);
	}

	/**
	 * 
	 * @param bbTag
	 * @param bbText
	 * @param text
	 * @return
	 */
	public static String createBBCode(final String bbTag, final String bbText, final String text) {
		return (isNullOrEmpty(bbTag) || isNullOrEmpty(text)) ? "" : (isNullOrEmpty(bbText)) ? createBBCodeInternal(
				bbTag, text) : createBBCodeInternal(bbTag, bbText, text);
	}

	private static String createBBCodeInternal(final String bbTag, final String bbText, final String text) {
		return createBBBeginInternal(bbTag, bbText) + text + createBBEndInternal(bbTag);
	}
}
