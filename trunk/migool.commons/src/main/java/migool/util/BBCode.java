package migool.util;

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
	public static final String createBBBegin(final String bbTag) {
		return StringUtil.isNullOrEmpty(bbTag) ? "" : createBBBeginInternal(bbTag);
	}

	private static final String createBBBeginInternal(final String bbTag) {
		return "[" + bbTag + "]";
	}

	/**
	 * 
	 * @param bbTag
	 * @param bbText
	 * @return
	 */
	public static final String createBBBegin(final String bbTag, final String bbText) {
		return StringUtil.isNullOrEmpty(bbTag) ? "" : StringUtil.isNullOrEmpty(bbText) ? createBBBeginInternal(bbTag)
				: createBBBeginInternal(bbTag, bbText);
	}

	private static final String createBBBeginInternal(final String bbTag, final String bbText) {
		return "[" + bbTag + "=" + bbText + "]";
	}

	/**
	 * 
	 * @param bbTag
	 * @return
	 */
	public static final String createBBEnd(final String bbTag) {
		return StringUtil.isNullOrEmpty(bbTag) ? "" : createBBEndInternal(bbTag);
	}

	private static final String createBBEndInternal(final String bbTag) {
		return "[/" + bbTag + "]";
	}

	public static final String createBBCode(final String bbTag) {
		return "[" + bbTag + "]";
	}

	/**
	 * 
	 * @param bb
	 * @param text
	 * @return [bb]text[/bb]
	 */
	public static final String createBBCode(final String bbTag, final String text) {
		return (StringUtil.isNullOrEmpty(bbTag) || StringUtil.isNullOrEmpty(text)) ? "" : createBBCodeInternal(bbTag,
				text);
	}

	private static final String createBBCodeInternal(final String bbTag, final String text) {
		return createBBBeginInternal(bbTag) + text + createBBEndInternal(bbTag);
	}

	/**
	 * 
	 * @param bbTag
	 * @param bbText
	 * @param text
	 * @return
	 */
	public static final String createBBCode(final String bbTag, final String bbText, final String text) {
		return (StringUtil.isNullOrEmpty(bbTag) || StringUtil.isNullOrEmpty(text)) ? "" : (StringUtil
				.isNullOrEmpty(bbText)) ? createBBCodeInternal(bbTag, text) : createBBCodeInternal(bbTag, bbText, text);
	}

	private static final String createBBCodeInternal(final String bbTag, final String bbText, final String text) {
		return createBBBeginInternal(bbTag, bbText) + text + createBBEndInternal(bbTag);
	}
}
