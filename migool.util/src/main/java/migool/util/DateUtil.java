package migool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Denis Migol
 * 
 */
public final class DateUtil {
	private static final String[] DATE_FORMATS = { "EEE, dd-MMM-yyyy hh:mm:ss z", "EEE, dd MMM yyyy hh:mm:ss z",
			"EEE MMM dd hh:mm:ss yyyy", // Sun Oct 13 10:50:15 2013
			"EEE MMM dd hh:mm:ss yyyy z", // Thu Dec 15 15:39:08 2011 GMT
			"EEE, dd-MM-yyyy hh:mm:ss z", // Tue, 14-10-2018 02:04:31 GMT
			"E, dd-MMM-yyyy hh:mm:ss a z", // Wednesday, 07-Dec-2011 12:40:25 AM
			"EEE MMM dd hh:mm:ss z yyyy" // Wed Feb 18 12:11:14 EET 2009
	};

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final Date parse(String date) {
		Date result = null;
		if (date != null) {
			for (int i = 0; i < DATE_FORMATS.length; i++) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATS[i], Locale.ENGLISH);
				try {
					result = dateFormat.parse(date);
				} catch (ParseException e) {
				}
			}
		}
		return result;
	}
	
//	/**
//	 * Test.
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		System.out.println(parse("Sun Oct 13 10:50:15 2013"));
//		System.out.println(parse("Thu Dec 15 15:39:08 2011 GMT"));
//		System.out.println(parse("Tue, 14-10-2018 02:04:31 GMT"));
//		System.out.println(parse("Wednesday, 07-Dec-2011 12:40:25 AM GMT"));
//		System.out.println(parse("Wed Feb 18 12:11:14 EET 2009"));
//	}
}
